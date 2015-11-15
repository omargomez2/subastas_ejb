/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationclient;

//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.osgg.ejb.entities.Postor;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.entities.Subasta;
import net.osgg.ejb.entities.Subastador;
import net.osgg.ejb.sessions.GenIDRemote;
import net.osgg.ejb.sessions.managers.PostorMgrRemote;
import net.osgg.ejb.sessions.managers.ServiciosMgrRemote;
import net.osgg.ejb.sessions.managers.SubastadorMgrRemote;
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
public class ClientEJB {

    /**
     * @param args the command line arguments
     */  
     
    private ServiciosMgrRemote lookupServiciosFacade() {
        try {
            Context ctx = new InitialContext();                
            return (ServiciosMgrRemote) ctx.lookup("ServiciosMgr");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
     }
    
    private SubastadorMgrRemote lookupSubastadorFacade() {
        try {
            Context ctx = new InitialContext();                
            return (SubastadorMgrRemote) ctx.lookup("SubastadorMgr");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
     }
    
     private PostorMgrRemote lookupPostorMgr() {
        try {
            Context ctx = new InitialContext();                
            return (PostorMgrRemote) ctx.lookup("PostorMgr");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
     }
    
     private GenIDRemote lookupIDGen() {
        try {
            Context ctx = new InitialContext();                
            return (GenIDRemote) ctx.lookup("GenIDBean");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
     }
    
     
    public void probarSubastador( ClientEJB t, GenIDRemote genID ){
      try {
            Subasta s = new Subasta();
            s.setIdSubasta(genID.getUUID());
            s.setNombre("subasta 2");
            s.setArticulo("Botella");
            s.setImgArticulo("/images/tequila.jpg");
            s.setPrecioInicial(0);

            Date d;
            GregorianCalendar gc = new GregorianCalendar(2007, 12-1, 12, 12, 00);
            d = gc.getTime();
            s.setFechaHoraInicio(d.getTime()); //Inicio Subasta
            
            
            gc = new GregorianCalendar(2015, 12-1, 12, 15, 00);
            d = gc.getTime();
            s.setFechaHoraFin(d.getTime());
            
            SubastadorMgrRemote subastador = t.lookupSubastadorFacade();
            subastador.iniciarSubasta(s); //Fin de Subasta

            //Subastador s = new Subastador();
            //s.setIdSubastador(genID.getUUID());
            //s.setIdSubastador("ea65e1d0a68c11dc8b5aeb86ac1001dc");
            //s.setNombre("Salvador");
            //s.setEmail("s@dominio.com");
            //SubastadorFacadeRemote sf = t.lookupSubastadorFacade();

            //Subastador s2 = new Subastador();
            //s2 = sf.find( s.getIdSubastador() );
            //System.out.println( s2.getNombre());
            //sf.create(s);
            //List <Subastador> myList = sf.findAll();
            //Iterator <Subastador>  it = myList.iterator() ;
            //while  (it.hasNext()){
            //   s =  it.next();
            //   System.out.println ( s.getNombre() ) ;
            //}
        } catch (EntradaNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FechaHoraNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }      
    } 
     
    
    public void listaPujas( ClientEJB t, Puja puja){
        ServiciosMgrRemote sMgr = t.lookupServiciosFacade();
        List<Puja> pujas = sMgr.getPujas("1303b2d0a80311dc9dcf18fcac1001dc");
        
        Iterator <Puja>  it = pujas.iterator() ; 
        while  (it.hasNext()){  
           puja =  it.next();  
           System.out.println ( puja.getIdPuja() + " " + puja.getIdSubasta() + " " + puja.getPrecio() ); 
        }         
        
        
        System.out.println( pujas.get(pujas.size() -1 ).getPrecio() );
    }
    
    public void iniciarSubasta( Subasta subasta){
        try {
            SubastadorMgrRemote subastador = lookupSubastadorFacade();
            subastador.iniciarSubasta(subasta); //Fin de Subasta
        } catch (EntradaNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FechaHoraNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void realizarPuja( Puja puja ){
        try {
            PostorMgrRemote postorMgr = lookupPostorMgr();
            postorMgr.realizarPuja(puja);
        } catch (PrecioPujaNoValidoException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PujaDuplicadaporPostorException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FechaHoraNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SubastasException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public String getID(){
       GenIDRemote genID = lookupIDGen(); 
       return genID.getUUID();
    }
    
    public void entrarSubasta(String idPostor, String idSubasta){
        try {
            PostorMgrRemote postorMgr = lookupPostorMgr();
            postorMgr.entrarSubasta(idPostor, idSubasta);
        } catch (EntradaNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RegistroSubastaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salirSubasta(String idPostor, String idSubasta){
        try {
            PostorMgrRemote postorMgr = lookupPostorMgr();
            postorMgr.salirSubasta(idPostor, idSubasta);
        } catch (EntradaNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RegistroSubastaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main( String[] args) throws PrecioPujaNoValidoException, PujaDuplicadaporPostorException, FechaHoraNoValidaException, SubastasException{
            ClientEJB client = new ClientEJB();
            Date date;
            GregorianCalendar gc;
           
            /*
           //----- Iniciar Subasta ------
            Subasta s = new Subasta();
            s.setIdSubasta( client.getID() );
            s.setNombre("Subasta I");
            s.setArticulo("Botella de Tequila 7 Leguas");
            s.setImgArticulo("/images/7leguas.jpg");
            s.setPrecioInicial(0.0);
            gc = new GregorianCalendar(2015, 1-1, 16, 11, 00); //Fecha Inicio Subasta
            date = gc.getTime();
            s.setFechaHoraInicio(date.getTime()); 
            gc = new GregorianCalendar(2015, 1-1, 16, 23, 00); //Fecha Fin Subasta
            date = gc.getTime();
            s.setFechaHoraFin(date.getTime());    
            client.iniciarSubasta(s);
          //------ Fin Iniciar subasta ------
          */
            
            
            
         //------ Entrar Subasta           idPostor                              idSubasta
           // client.entrarSubasta("a1bc8ed0c42711dc9681005b8a64d9a7", "4bce0820c42511dcaa3143be8a64d9a7");
           // client.salirSubasta("a1bc8ed0c42711dc9681005b8a64d9a7", "4bce0820c42511dcaa3143be8a64d9a7");
         //------ Fin Entrar subasta   
            
            
          //------ Realizar Puja ------   
            Puja puja = new Puja();
            puja.setIdPuja( ClientEJB.getID());
            puja.setIdSubasta("4bce0820c42511dcaa3143be8a64d9a7");
            puja.setIdPostor("a1bc8ed0c42711dc9681005b8a64d9a7");
            puja.setPrecio(123.12);

            gc = new GregorianCalendar(2015, 1-1, 16, 12, 10);
            date = gc.getTime();
            puja.setFechaHora(date.getTime());
            puja.setEstado('e'); // e: entrante a:aceptada r:rechazada
            client.realizarPuja(puja);
          //------ Fin Realizar Puja -----
              
        
    }        
      
}



        /*
        
        try {
            //try {
            
            GenIDRemote genID = t.lookupIDGen();

            
            Puja puja = new Puja();
            puja.setIdPuja(genID.getUUID());
            puja.setIdSubasta("1303b2d0a80311dc9dcf18fcac1001dc");
            puja.setIdPostor("22");
            puja.setPrecio(666.69);

            Date d;
            GregorianCalendar gc = new GregorianCalendar(2015, 12 - 1, 12, 12, 10);
            d = gc.getTime();

            puja.setFechaHora(d.getTime());
            puja.setEstado('e');


            PostorMgrRemote postorMgr = t.lookupPostorMgr();
            //postorMgr.entrarSubasta("1", "1303b2d0a80311dc9dcf18fcac1001dc");

            //postorMgr.entrarSubasta("1", "1303b2d0a80311dc9dcf18fcac1001dc");
            postorMgr.realizarPuja(puja);


            } catch (PujaDuplicadaporPostorException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FechaHoraNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
             
*/

            //Subastador s = new Subastador();
            //s.setIdSubastador(genID.getUUID());
            //s.setIdSubastador("ea65e1d0a68c11dc8b5aeb86ac1001dc");
            //s.setNombre("Salvador|");
            //s.setEmail("s@dominio.com");
            //SubastadorFacadeRemote sf = t.lookupSubastadorFacade();

            //Subastador s2 = new Subastador();
            //s2 = sf.find( s.getIdSubastador() );
            //System.out.println( s2.getNombre());
            //sf.create(s);
            //List <Subastador> myList = sf.findAll();
            //Iterator <Subastador>  it = myList.iterator() ;
            //while  (it.hasNext()){
            //   s =  it.next();
            //   System.out.println ( s.getNombre() ) ;
            //}
        //} catch (RegistroSubastaException ex) {
        //    Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (EntradaNoValidaException ex) {
        //    Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        //}
            
           // postorMgr.realizarPuja(puja);

/*
        } catch (PrecioPujaNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PujaDuplicadaporPostorException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FechaHoraNoValidaException ex) {
            Logger.getLogger(ClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */
           
        
        
        //Subastador s = new Subastador();
        //s.setIdSubastador(genID.getUUID());
   
        //s.setIdSubastador("ea65e1d0a68c11dc8b5aeb86ac1001dc");
        //s.setNombre("Salvador");
        //s.setEmail("s@dominio.com");
        
        //SubastadorFacadeRemote sf = t.lookupSubastadorFacade();
        

        //Subastador s2 = new Subastador();
        //s2 = sf.find( s.getIdSubastador() );
        //System.out.println( s2.getNombre());
        
        //sf.create(s);
        //List <Subastador> myList = sf.findAll();
        //Iterator <Subastador>  it = myList.iterator() ; 
        //while  (it.hasNext()){  
        //   s =  it.next();  
        //   System.out.println ( s.getNombre() ) ; 
        //} 
