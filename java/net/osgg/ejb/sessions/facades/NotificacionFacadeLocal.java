/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.facades;

import java.util.List;
import javax.ejb.Local;
import net.osgg.ejb.entities.Notificacion;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface NotificacionFacadeLocal {

    void create(Notificacion notificacion);

    void edit(Notificacion notificacion);

    void remove(Notificacion notificacion);

    Notificacion find(Object id);

    List<Notificacion> findAll();

    public List<Notificacion> getNotificaciones(String idSubasta);

}
