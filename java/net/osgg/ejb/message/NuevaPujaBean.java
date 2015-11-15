/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.message;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import net.osgg.ejb.entities.Puja;
import net.osgg.ejb.sessions.facades.PujaFacadeLocal;

/**
 *
 * @author omar s. gómez, 2015
 */
@MessageDriven(mappedName = "jms/NuevaPuja", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class NuevaPujaBean implements MessageListener {
    @EJB 
    private PujaFacadeLocal pujaFacade;  
    
    @Resource
    private MessageDrivenContext mdc;
    
    public NuevaPujaBean() {
    }

    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                Puja puja = (Puja)msg.getObject();
                save(puja);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

    private void save(Puja puja) {
        pujaFacade.create(puja);
    }
    
}
