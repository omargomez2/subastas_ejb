/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.osgg.ejb.entities.Postor;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.entities.Subasta;
import net.osgg.ejb.sessions.facades.PujaFacadeLocal;
import net.osgg.ejb.sessions.facades.SubastaFacadeLocal;
import net.osgg.exceptions.EntradaNoValidaException;
import net.osgg.exceptions.FechaHoraNoValidaException;
import net.osgg.exceptions.SubastasException;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless(mappedName="SubastadorMgr")
public class SubastadorMgrBean implements SubastadorMgrRemote {
    
    @EJB 
    private SubastaFacadeLocal subastaFacade;
    @EJB
    private PujaFacadeLocal pujaFacade;
    @EJB
    private ServiciosMgrLocal serviciosMgr;

    private Subasta subastaG;
    private Postor postorG;
    
    
    
    /*  OCL Semantic
        context ISubastadorMgr::iniciarSubasta( subasta :Subasta )
        pre:
            subasta.idSubasta <> null and
            subasta.nombre <> null and
            subasta.articulo <> null and
            subasta.fechaHoraInicio > 0 and
            subasta.fechaHoraFin > 0 and
            subasta.precioInicial >= 0 and
            subasta.fechaHoraInicio < subasta.HoraFin
        post: 
            subastas = subastas@pre->including( subasta )
    */
    public void iniciarSubasta(Subasta subasta) throws EntradaNoValidaException, FechaHoraNoValidaException {
        if (subasta.getIdSubasta() == null )
            throw new EntradaNoValidaException("Id de subasta no debe estar en blanco");
        else if (subasta.getNombre() == null )
            throw new EntradaNoValidaException("Nombre de subasta no debe estar en blanco");
        else if (subasta.getArticulo() == null )     
            throw new EntradaNoValidaException("El articulo no debe estar en blanco");   
        else if (subasta.getFechaHoraInicio() <= 0 ) 
            throw new EntradaNoValidaException("La fecha y hora de inicio deben ser mayores a 0");
        else if (subasta.getFechaHoraFin() <= 0 )
            throw new EntradaNoValidaException("La fecha y hora final deben ser mayores a 0"); 
        else if (subasta.getPrecioInicial() < 0.0)
            throw new EntradaNoValidaException("El precio del articulo debe ser mayor o igual que 0");
        else if (subasta.getFechaHoraInicio() >= subasta.getFechaHoraFin() )
            throw new FechaHoraNoValidaException("La fecha y hora inicial no deben ser mayores que la fecha hora final"); 
        else
            subastaFacade.create(subasta);
    }
    
    
    
    /*  OCL Semantic
        context  ISubastadorMgr::adjudicarPuja( puja :Puja )
        inv:
            --Las pujas almacenadas son pujas validas de alguna subasta
            puja.fechaHora >= Subasta.fechaHoraInicio and
            puja.fechaHora <= Subasta.fechaHoraFin
        pre: 
            puja.precio > pujas.precio->last() and
            puja.estado = ‘En espera’ and
            Subasta.fechaHoraFin > IServiciosMgr::getSystemDateTime()
        post: 
            notificaciones = notificaciones@pre->including( Notificacion )
     */
    public void adjudicarPuja(Puja puja) throws SubastasException, FechaHoraNoValidaException {
        subastaG = subastaFacade.find(puja.getIdSubasta());
        postorG = serviciosMgr.getPostor(puja.getIdPostor());
        if ( puja.getPrecio() <= serviciosMgr.getPujaUltimoPrecio(puja.getIdSubasta()).getPrecio() )
           throw new SubastasException("El precio de la puja debe ser el mayor"); 
        else if ( puja.getEstado() != 'e' )
           throw new SubastasException("El estado de la puja debe estar en espera"); 
        else if ( !serviciosMgr.isSubastaFianlizada(subastaG) )
           throw new FechaHoraNoValidaException("Para poder adjudicar esta puja, el tiempo de la subasta debe haber terminado");
        else  
         serviciosMgr.addNotificacion(subastaG.getIdSubasta(), "adjudicada", serviciosMgr.getSystemDateTimeFormated() + "-- El articulo "+
                                  subastaG.getArticulo()+ "con el precio de " +
                                  puja.getPrecio() + "lo ha adjudicado " + postorG.getNombre() );     
    }

    
    
    /*  OCL Semantic
        context  ISubastadorMgr::aceptarPuja( puja :Puja )
        inv:
            --Las pujas almacenadas son pujas validas de alguna subasta
            puja.fechaHora >= Subasta.fechaHoraInicio and
            puja.fechaHora <= Subasta.fechaHoraFin
        pre:
            puja.precio > pujas.precio->last()
        post:
            puja.estado = ‘Aceptada’
        notificaciones = notificaciones@pre->including( Notificacion )
     */
    public void aceptarPuja(Puja puja) throws SubastasException {
        subastaG = subastaFacade.find(puja.getIdSubasta());
        postorG = serviciosMgr.getPostor(puja.getIdPostor());     
        if ( puja.getPrecio() <=  serviciosMgr.getPujaUltimoPrecio(puja.getIdSubasta()).getPrecio() )
           throw new SubastasException("El precio de la puja debe ser el mayor"); 
       else {
          puja.setEstado('a');
          pujaFacade.edit(puja);
          serviciosMgr.addNotificacion(subastaG.getIdSubasta(), "aceptada", serviciosMgr.getSystemDateTimeFormated() +"-- La puja enviada por "+
                                 postorG.getNombre()+"del articulo " +subastaG.getArticulo()+ 
                                 "con el precio de " + puja.getPrecio() + "ha sido aceptada" );  
       }
    }

    
    
    /*  OCL Semantic
        context  ISubastadorMgr::rechazarPuja( puja: Puja)
        inv:
            --Las pujas almacenadas son pujas validas de alguna subasta
            puja.fechaHora >= Subasta.fechaHoraInicio and
            puja.fechaHora <= Subasta.fechaHoraFin
        pre:
            puja.precio > pujas.precio->last()
        post:
            puja.estado = ‘Rechazada’
            notificaciones = notificaciones@pre->including( Notificacion )
     */ 
    public void rechazarPuja(Puja puja) throws SubastasException{
        subastaG = subastaFacade.find(puja.getIdSubasta());
        postorG = serviciosMgr.getPostor(puja.getIdPostor());      
        if ( puja.getPrecio() <= serviciosMgr.getPujaUltimoPrecio(puja.getIdSubasta()).getPrecio() )
           throw new SubastasException("El precio de la puja debe ser el mayor"); 
       else {
          puja.setEstado('r');
          pujaFacade.edit(puja);
          serviciosMgr.addNotificacion(subastaG.getIdSubasta(), "rechazada", serviciosMgr.getSystemDateTimeFormated() +"-- La puja enviada por "+
                                  postorG.getNombre()+"del articulo " +subastaG.getArticulo()+ 
                                  "con el precio de " +puja.getPrecio() + "ha sido rechazada" );  
       }        
    }
 
}
