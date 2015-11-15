/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.demo;

    import java.util.Date;
    import java.text.DateFormat;
    import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import net.osgg.ejb.entities.Subasta;
/**
 *
 * @author omar s. gómez, 2015
 */


public class Test {
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
        
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
  
      Test t = new Test();
      String s = t.getDateTime();
      System.out.println(s);
      
      Date d;
      GregorianCalendar gc = new GregorianCalendar(2007, 12-1, 12, 12, 00);
      d = gc.getTime();
      System.out.println(d.getTime());
      
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      dateFormat.format(d);
      System.out.println( dateFormat.format(d) ); 
      
      
      
            Subasta su = new Subasta();
            //ss.setIdSubasta(genID.getUUID());
            //s.setArticulo("Botella de Tequila");
            su.setNombre("subasta chidaff");
            //su.setPrecioInicial(300.50);
      if (su.getArticulo() == null )
            System.out.print(su.getArticulo());
      
    }
}
