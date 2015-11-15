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
public class RegistroSubasta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    private String idPostor;
    private String idSubasta;
    private char estado;
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getIdPostor() {
        return idPostor;
    }

    public void setIdPostor(String idPostor) {
        this.idPostor = idPostor;
    }

    public String getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(String idSubasta) {
        this.idSubasta = idSubasta;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroSubasta)) {
            return false;
        }
        RegistroSubasta other = (RegistroSubasta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "net.osgg.ejb.entities.RegistroSubasta[id=" + id + "]";
    }

}
