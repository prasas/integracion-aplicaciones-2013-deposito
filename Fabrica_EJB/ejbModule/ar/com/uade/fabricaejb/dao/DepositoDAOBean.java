package ar.com.uade.fabricaejb.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import clientes.GenericQueueClient;
import parsers.ParserException;
import parsers.SolicitudCompraXMLParser;
import dto.SolicitudCompraDTO;


/**
 * Session Bean implementation class DepositoDAOBean
 */
@Stateless
@LocalBean
public class DepositoDAOBean  {
	
	private static final Logger logger = Logger.getLogger(DepositoDAOBean.class);


	private SolicitudCompraXMLParser parser=SolicitudCompraXMLParser.obtenerInstancia();
	
	public void entregarCompra(SolicitudCompraDTO compra) {

		try {
			String xml=null;
			xml = parser.toString(compra);
			enviar(xml);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void enviar(String xml) {
		String errorMessage = new String();
		//TODO: Ajustar y habilitar esto
		String  localMachine =null;
		try {
			
			localMachine=java.net.InetAddress.getLocalHost().getHostAddress();
			
			}
			catch (java.net.UnknownHostException uhe) { // [beware typo in
				uhe.printStackTrace();
			}
		
		String ipStr=localMachine;//.getAddress()[0] + "."+ localMachine.getAddress()[1]+ "." + localMachine.getAddress()[2]+ "." + localMachine.getAddress()[3];
		
		try {
			GenericQueueClient cliente = new GenericQueueClient("jms/queue/recepcionCompra", ipStr , "4447", "deposito", "deposito123");
			cliente.enviar(xml);
			cliente.cerrarConexion();
		} catch (JMSException e) {
			errorMessage = "*** Error enviando xml a jms de DEPOSITO IP[" + ipStr + "] ***";
			logger.error(errorMessage, e);
		} catch (NamingException e) {
			errorMessage = "*** Error conectando a cola jms de DEPOSITO IP[" + ipStr + "] ***";
			logger.error(errorMessage, e);
		}
	}
}
