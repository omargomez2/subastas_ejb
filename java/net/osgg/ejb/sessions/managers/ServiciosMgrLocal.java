/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import javax.ejb.Local;
import net.osgg.ejb.entities.Notificacion;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.entities.Subasta;
import net.osgg.exceptions.FechaHoraNoValidaException;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface ServiciosMgrLocal {

    public long getSystemDateTime();

    public Subasta getSubasta(String idSubasta);

    public java.util.List<Subasta> getAllSubastas();

    public Puja getPuja(java.lang.String idPuja);

    public java.util.List<Puja> getPujas(String idSubasta);

    public java.util.List<Notificacion> getNotificaciones(String idSubasta);

    public net.osgg.ejb.entities.Postor getPostor(String idPostor);

    public boolean abandonoSubasta(String idPostor, String idSubasta);

    public java.lang.String getSystemDateTimeFormated();

    public Puja getPujaUltimoPrecio(String idSubasta);

    public void addNotificacion(java.lang.String idSubasta, java.lang.String tipo, java.lang.String texto);

    public boolean isPujaEnTiempo(Puja puja);

    public boolean isSubastaFianlizada(Subasta subasta);

    public boolean isPostorDentroSubasta(java.lang.String idPostor, java.lang.String idSubasta);

    public java.lang.String getUUID();

    public boolean isPostorFueraSubasta(java.lang.String idPostor, java.lang.String idSubasta);
    
}
