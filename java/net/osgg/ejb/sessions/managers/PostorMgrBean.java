/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import net.osgg.ejb.entities.Postor;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.entities.RegistroSubasta;
import net.osgg.ejb.entities.Subasta;
import net.osgg.ejb.sessions.crud.PujaCRUDLocal;
import net.osgg.ejb.sessions.crud.RegistroSubastaCRUDLocal;
import net.osgg.ejb.sessions.crud.SubastaCRUDLocal;
import net.osgg.exceptions.EntradaNoValidaException;
import net.osgg.exceptions.FechaHoraNoValidaException;
import net.osgg.exceptions.PrecioPujaNoValidoException;
import net.osgg.exceptions.PujaDuplicadaporPostorException;
import net.osgg.exceptions.RegistroSubastaException;
import net.osgg.exceptions.SubastasException;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless(mappedName="PostorMgr")
public class PostorMgrBean implements PostorMgrRemote {
    @EJB 
    private PujaCRUDLocal pujaCRUD;   
    @EJB
    private SubastaCRUDLocal subastaCRUD;
    @EJB
    private RegistroSubastaCRUDLocal rSubastaCRUD;
    @EJB
    private ServiciosMgrLocal serviciosMgr;
    
    @Resource(mappedName="jms/NuevaPujaFactory")
    private  ConnectionFactory connectionFactory;

    @Resource(mappedName="jms/NuevaPuja")
    private  Queue queue;
    
    
    private Subasta subastaG;
    private Postor postorG;
    private RegistroSubasta rSubasta;

    
    /*  OCL Semantic
        context  IPostorMgr::realizarPuja( puja : Puja )
        pre:
            puja.precio > pujas.precio->last() and
            puja.fechaHora >= Subasta.fechaHoraInicio and
            puja.fechaHora <= Subasta.fechaHoraFin and
            Postor.idPostor <> pujas.Postor.idPostor->last() and 
            subastasActualesIngresadas->includes(Postor) and
            not subastasAbandonadas->includes(Postor)
        post: 
        pujas = pujas@pre->including( puja )
     */
    public void realizarPuja(Puja puja) throws PrecioPujaNoValidoException, PujaDuplicadaporPostorException, FechaHoraNoValidaException, SubastasException {
        if (puja.getPrecio() <= serviciosMgr.getPujaUltimoPrecio( puja.getIdSubasta() ).getPrecio() )
            throw new PrecioPujaNoValidoException("El precio de la puja debe ser mayor al anterior");
        else if ( !serviciosMgr.isPujaEnTiempo(puja) )
            throw new FechaHoraNoValidaException("La fecha y hora de la puja debe estar dentro de los tiempos de la subasta");
        else if ( puja.getIdPostor().equals( pujaCRUD.getPujaUltimoPrecio(puja.getIdSubasta()).getIdPostor() ) )  
            throw new PujaDuplicadaporPostorException("El postor no debe volver a pujar hasta que reciba un nuevo precio por otro postor");
        else if ( !serviciosMgr.isPostorDentroSubasta(puja.getIdPostor(), puja.getIdSubasta()) )
            throw new SubastasException("El Postor debe estar dentro de alguna subasta");
        else            
            encolaPuja(puja);    
    }
    
    private void encolaPuja(Puja puja){
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            ObjectMessage message = session.createObjectMessage();
            message.setObject(puja);
            messageProducer.send(message);
            messageProducer.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }  
    }
    
    
    /*  OCL Semantic
        context  IPostorMgr::entrarSubasta( idPostor :String, idSubasta :String )
        pre:
            idPostor <> null and
            idSubasta <> null 
        post:
            if not registroSubastas->exists( idSubasta = RegistroSubasta.idSubasta ) then
                registroSubastas = registroSubastas@pre->including( Subasta )
                notificaciones = notificaciones@pre->including( Notificacion )
            endif
     */ 
    public void entrarSubasta(String idPostor, String idSubasta) throws EntradaNoValidaException, RegistroSubastaException {
        if (idSubasta == null )
            throw new EntradaNoValidaException("Id de subasta no debe estar en blanco");
        else if (idPostor == null )
            throw new EntradaNoValidaException("Id de postor no debe estar en blanco");       
        else if (serviciosMgr.isPostorDentroSubasta(idPostor, idSubasta)) 
            throw new RegistroSubastaException("El Postor ya esta dentro de la subasta");
        else if (serviciosMgr.abandonoSubasta(idPostor, idSubasta))
            throw new RegistroSubastaException("El Postor ya ha abandonado la subasta");     
        else if (rSubastaCRUD.getRegistroSubasta(idPostor, idSubasta).getId() == null){
                rSubasta = new RegistroSubasta();
                rSubasta.setId( serviciosMgr.getUUID() );
                rSubasta.setIdPostor(idPostor);
                rSubasta.setIdSubasta(idSubasta);
                rSubasta.setEstado('e');
                rSubastaCRUD.create(rSubasta);
                postorG = serviciosMgr.getPostor(idPostor);
                subastaG = subastaCRUD.find(idSubasta);
                serviciosMgr.addNotificacion(idSubasta, "entrada",serviciosMgr.getSystemDateTimeFormated() +"-- El Postor "+postorG.getNombre()+
                                    " ha entrado a la subasta "+subastaG.getNombre());
        } else if ( serviciosMgr.isPostorFueraSubasta(idPostor, idSubasta)){
                rSubasta = rSubastaCRUD.getRegistroSubasta(idPostor, idSubasta);
                rSubasta.setEstado('e');
                rSubastaCRUD.edit(rSubasta);
                postorG = serviciosMgr.getPostor(idPostor);
                subastaG = subastaCRUD.find(idSubasta);
                serviciosMgr.addNotificacion(idSubasta, "entrada",serviciosMgr.getSystemDateTimeFormated() +"-- El Postor "+postorG.getNombre()+
                                    " ha entrado a la subasta "+subastaG.getNombre());           
        }    
                
    }
    
    
    
    /*  OCL Semantic
        context  IPostorMgr::salirSubasta( idPostor :String, idSubasta :String )
        pre:
           idPostor <> null and
           idSubasta <> null and
           registroSubastas->includes( idPostor, idSubasta )
        post:
          registroSubasta.estado = ‘s’
          notificaciones = notificaciones@pre->including( Notificacion )
    */ 
    public void salirSubasta(String idPostor, String idSubasta) throws EntradaNoValidaException, RegistroSubastaException {
        if (idSubasta == null )
            throw new EntradaNoValidaException("Id de subasta no debe estar en blanco");
        else if (idPostor == null )
            throw new EntradaNoValidaException("Id de postor no debe estar en blanco"); 
        else if (rSubastaCRUD.getRegistroSubasta(idPostor, idSubasta).getId() == null)
            throw new RegistroSubastaException("Debe existir un idPostor e idSubasta validos en el registro de subastas");
        else if ( serviciosMgr.isPostorFueraSubasta(idPostor, idSubasta) )
            throw new RegistroSubastaException("El Postor ya ha salido de la subasta");
        else if ( serviciosMgr.abandonoSubasta(idPostor, idSubasta))
            throw new RegistroSubastaException("El Postor ya ha abandonado la subasta");
        else{
                rSubasta = rSubastaCRUD.getRegistroSubasta(idPostor, idSubasta);
                rSubasta.setEstado('s');
                rSubastaCRUD.edit(rSubasta);
                postorG = serviciosMgr.getPostor(idPostor);
                subastaG = subastaCRUD.find(idSubasta);
                serviciosMgr.addNotificacion(idSubasta, "salida",serviciosMgr.getSystemDateTimeFormated() +"-- El Postor "+postorG.getNombre()+
                                    " ha salido de la subasta "+subastaG.getNombre());  
        } 
    }
    
    
    
    /*  OCL Semantic
        context  IPostorMgr::abandonarSubasta( idPostor :String, idSubasta :String, )
        pre:
            idPostor <> null and
            idSubasta <> null and
            registroSubastas->includes( idPostor, idSubasta )
        post: 
            registroSubasta.estado = ‘a’
     */ 
    public void abandonarSubasta(String idPostor, String idSubasta) throws EntradaNoValidaException, RegistroSubastaException {
         if (idSubasta == null )
            throw new EntradaNoValidaException("Id de subasta no debe estar en blanco");
        else if (idPostor == null )
            throw new EntradaNoValidaException("Id de postor no debe estar en blanco"); 
        else if (rSubastaCRUD.getRegistroSubasta(idPostor, idSubasta).getId() == null)
            throw new RegistroSubastaException("Debe existir un idPostor e idSubasta validos en el registro de subastas");
        else if ( serviciosMgr.isPostorFueraSubasta(idPostor, idSubasta))
            throw new RegistroSubastaException("Para poder abandonar la subasta, el postor debe estar dentro de ésta");        
        else if ( serviciosMgr.abandonoSubasta(idPostor, idSubasta))
            throw new RegistroSubastaException("El Postor ya ha abandonado esta subasta"); 
        else{
                rSubasta = rSubastaCRUD.getRegistroSubasta(idPostor, idSubasta);
                rSubasta.setEstado('a');
                rSubastaCRUD.edit(rSubasta);
                postorG = serviciosMgr.getPostor(idPostor);
                subastaG = subastaCRUD.find(idSubasta);
                serviciosMgr.addNotificacion(idSubasta, "abandono",serviciosMgr.getSystemDateTimeFormated() +"-- El Postor "+postorG.getNombre()+
                                    " ha abandonado la subasta "+subastaG.getNombre());  
        }       
    }   
}
