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
public class Postor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String idPostor;
    private String nombre;
    private String email;
    
    public void setIdPostor(String idPostor) {
        this.idPostor = idPostor;
    }

    public String getIdPostor() {
        return idPostor;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postor)) {
            return false;
        }
        Postor other = (Postor) object;
        if ((this.idPostor == null && other.idPostor != null) || (this.idPostor != null && !this.idPostor.equals(other.idPostor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.idPostor != null ? this.idPostor.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "net.osgg.ejb.entities.Postor[id=" + idPostor + "]";
    }

}
