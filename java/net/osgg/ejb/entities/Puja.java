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
public class Puja implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
    private String idPuja;
    private String idSubasta;
    private String idPostor;
    private double precio;
    private long fechaHora;
    private char estado;
       
    
    public void setIdPuja(String idPuja) {
        this.idPuja = idPuja;
    }

    
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public String getIdPuja() {
        return idPuja;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(long fechaHora) {
        this.fechaHora = fechaHora;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuja != null ? idPuja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.idPuja == null && other.idPuja != null) || (this.idPuja != null && !this.idPuja.equals(other.idPuja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.osgg.ejb.entities.Puja[id=" + idPuja + "]";
    }

}
