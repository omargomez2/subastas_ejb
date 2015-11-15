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
public class Subasta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String idSubasta;

    private String nombre;
    private long fechaHoraInicio;
    private long fechaHoraFin;
    private String articulo;
    private String imgArticulo;
    private double precioInicial;

    
    
    public void setIdSubasta(String idSubasta) {
        this.idSubasta = idSubasta;
    }

    public String getIdSubasta() {
        return idSubasta;
    }
    
    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getImgArticulo() {
        return imgArticulo;
    }

    public void setImgArticulo(String imgArticulo) {
        this.imgArticulo = imgArticulo;
    }
    
    public long getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(long fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public long getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(long fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(double precioInicial) {
        this.precioInicial = precioInicial;
    }

    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subasta)) {
            return false;
        }
        Subasta other = (Subasta) object;
        if ((this.idSubasta == null && other.idSubasta != null) || (this.idSubasta != null && !this.idSubasta.equals(other.idSubasta))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.idSubasta != null ? this.idSubasta.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "net.osgg.ejb.entities.Subasta[id=" + idSubasta + "]";
    }

}
