/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.osgg.ejb.entities.Notificacion;
import net.osgg.ejb.entities.Postor;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.entities.Subasta;
import net.osgg.ejb.sessions.GenIDLocal;
import net.osgg.ejb.sessions.facades.NotificacionFacadeLocal;
import net.osgg.ejb.sessions.facades.PostorFacadeLocal;
import net.osgg.ejb.sessions.facades.PujaFacadeLocal;
import net.osgg.ejb.sessions.facades.RegistroSubastaFacadeLocal;
import net.osgg.ejb.sessions.facades.SubastaFacadeLocal;



/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless(mappedName="ServiciosMgr")
public class ServiciosMgrBean implements ServiciosMgrRemote, ServiciosMgrLocal {

    @EJB
    private PujaFacadeLocal pujaFacade;
    @EJB
    private SubastaFacadeLocal subastaFacade;
    @EJB
    private GenIDLocal genId;
    @EJB
    private NotificacionFacadeLocal notificacionFacade;
    @EJB
    private PostorFacadeLocal postorFacade;
    @EJB
    private RegistroSubastaFacadeLocal rSubastaFacade;
    
    private Date date;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    
    public long getSystemDateTime() {
        date = new Date();   
        return date.getTime();
    }

    public String getSystemDateTimeFormated(){
        date = new Date();
        return dateFormat.format(date);
    }
    
    public boolean isSubastaFianlizada(Subasta subasta) {
        if ( getSystemDateTime() > subasta.getFechaHoraFin() )
            return true;
        else
            return false;
    }   
    
    public boolean isPujaEnTiempo(Puja puja) {
        Subasta subasta = subastaFacade.find(puja.getIdSubasta());
        if ( puja.getFechaHora() >= subasta.getFechaHoraInicio() && puja.getFechaHora() <= subasta.getFechaHoraFin() )
            return true;
        else 
            return false;
    }

    public Subasta getSubasta(String idSubasta) {
        return subastaFacade.find(idSubasta);
    }

    public List <Subasta> getAllSubastas() {
        return subastaFacade.findAll();
    }

    public Puja getPuja(String idPuja) {
        return pujaFacade.find(idPuja);
    }

    public List<Puja> getPujas(String idSubasta) {
        return pujaFacade.getPujas(idSubasta);
    }

    public Puja getPujaUltimoPrecio(String idSubasta) {
        return pujaFacade.getPujaUltimoPrecio(idSubasta);
    }

    public void addNotificacion(String idSubasta, String tipo, String texto) {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdNotificacion( genId.getUUID() );
        notificacion.setIdSubasta(idSubasta);
        notificacion.setDescripcion( texto  );
        notificacion.setTipo(tipo);
        notificacionFacade.create(notificacion);
    }

    public List<Notificacion> getNotificaciones(String idSubasta) {
        return notificacionFacade.getNotificaciones(idSubasta);
    }

    public Postor getPostor(String idPostor) {
        return postorFacade.find(idPostor);
    }

    public boolean abandonoSubasta(String idPostor, String idSubasta) {               
        if (rSubastaFacade.getRegistroSubasta(idPostor, idSubasta).getEstado() == 'a')
           return true;
        else 
           return false;
    }
    
    public boolean isPostorDentroSubasta(String idPostor, String idSubasta) {
        if (rSubastaFacade.getRegistroSubasta(idPostor, idSubasta).getEstado() == 'e')
           return true;
        else 
           return false;
    }
    
    public boolean isPostorFueraSubasta(String idPostor, String idSubasta) {
        if (rSubastaFacade.getRegistroSubasta(idPostor, idSubasta).getEstado() == 's')
           return true;
        else 
           return false;
    }
    
    
    public String getUUID(){
        return genId.getUUID();
    }
 
}

