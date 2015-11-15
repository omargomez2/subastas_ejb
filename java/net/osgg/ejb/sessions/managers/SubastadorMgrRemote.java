/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import javax.ejb.Remote;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.entities.Subasta;
import net.osgg.exceptions.EntradaNoValidaException;
import net.osgg.exceptions.FechaHoraNoValidaException;
import net.osgg.exceptions.SubastasException;

/**
 *
 * @author omar s. gómez, 2015
 */
@Remote
public interface SubastadorMgrRemote {
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
    void iniciarSubasta(Subasta subasta) throws EntradaNoValidaException, FechaHoraNoValidaException;
    
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
    void adjudicarPuja(Puja puja) throws SubastasException, FechaHoraNoValidaException;
    
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
    void aceptarPuja(Puja puja) throws SubastasException;
    
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
    void rechazarPuja(Puja puja) throws SubastasException;
    
}
