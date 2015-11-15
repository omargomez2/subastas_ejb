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
public class Subastador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String idSubastador;
    private String nombre;
    private String email;
   
    public String getIdSubastador() {
        return idSubastador;
    }
    
    public void setIdSubastador(String idSubastador) {
        this.idSubastador = idSubastador;
    }
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subastador)) {
            return false;
        }
        Subastador other = (Subastador) object;
        if ((this.idSubastador == null && other.idSubastador != null) || (this.idSubastador != null && !this.idSubastador.equals(other.idSubastador))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.idSubastador != null ? this.idSubastador.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "net.osgg.ejb.entities.Subastador[id=" + idSubastador + "]";
    }

}
