/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import javax.ejb.Remote;
import net.osgg.ejb.entities.Puja;
import net.osgg.exceptions.EntradaNoValidaException;
import net.osgg.exceptions.FechaHoraNoValidaException;
import net.osgg.exceptions.PrecioPujaNoValidoException;
import net.osgg.exceptions.PujaDuplicadaporPostorException;
import net.osgg.exceptions.RegistroSubastaException;
import net.osgg.exceptions.SubastasException;

/**
 *
 * @author omar s. gómez, 2015
 */
@Remote
public interface PostorMgrRemote {
    /*  OCL Semantic
        context  IPostorMgr::realizarPuja( puja : Puja )
        pre:
            puja.precio > pujas.precio->last() and
            puja.fechaHora >= Subasta.fechaHoraInicio and
            puja.fechaHora <= Subasta.fechaHoraFin and
            Postor.idPostor <> pujas.Postor.idPostor->last() and 
            subastasActualesIngresadas->includes(Postor) and
            not subastasAbandonadas->includes(Postor)
        post: 
        pujas = pujas@pre->including( puja )
     */
    void realizarPuja(Puja puja) throws PrecioPujaNoValidoException, PujaDuplicadaporPostorException, 
                                        FechaHoraNoValidaException, SubastasException;
    /*  OCL Semantic
        context  IPostorMgr::entrarSubasta( idPostor :String, idSubasta :String )
        pre:
            idPostor <> null and
            idSubasta <> null 
        post:
            if not registroSubastas->exists( idSubasta = RegistroSubasta.idSubasta ) then
                registroSubastas = registroSubastas@pre->including( Subasta )
                notificaciones = notificaciones@pre->including( Notificacion )
            endif
     */
    void entrarSubasta(String idPostor, String idSubasta) throws EntradaNoValidaException, RegistroSubastaException;
    
    /*  OCL Semantic
        context  IPostorMgr::salirSubasta( idPostor :String, idSubasta :String )
        pre:
           idPostor <> null and
           idSubasta <> null and
           registroSubastas->includes( idPostor, idSubasta )
        post:
          registroSubasta.estado = ‘s’
          notificaciones = notificaciones@pre->including( Notificacion )
    */ 
    void salirSubasta(String idPostor, String idSubasta) throws EntradaNoValidaException, RegistroSubastaException;
    
    /*  OCL Semantic
        context  IPostorMgr::abandonarSubasta( idPostor :String, idSubasta :String, )
        pre:
            idPostor <> null and
            idSubasta <> null and
            registroSubastas->includes( idPostor, idSubasta )
        post: 
            registroSubasta.estado = ‘a’
     */ 
    void abandonarSubasta(String idPostor, String idSubasta) throws EntradaNoValidaException, RegistroSubastaException;
    

    
}
