package jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import dto.SolicitudDTO;
import sessionBeans.Facade;

/**
 * Message-Driven Bean implementation class for: RecepcionSolicitudArticulos
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "aaaaaaaaaaa")
		}, 
		mappedName = "aaaaaaaaaaa")
public class RecepcionSolicitudArticulos implements MessageListener {

	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public RecepcionSolicitudArticulos() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        
    	SolicitudDTO solicitud = new SolicitudDTO();
		facade.recibirSolicitudArticulos(solicitud);
    }

}