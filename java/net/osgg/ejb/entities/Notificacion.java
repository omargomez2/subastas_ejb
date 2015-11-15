/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author omar s. gómez, 2015
 */
@Entity
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String idNotificacion;
    private String idSubasta;
    private String tipo;
    private String descripcion;
    
    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public String getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(String idSubasta) {
        this.idSubasta = idSubasta;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.idNotificacion != null ? this.idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "net.osgg.ejb.entities.Notificacion[id=" + idNotificacion + "]";
    }

}
