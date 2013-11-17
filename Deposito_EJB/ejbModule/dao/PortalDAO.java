package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import clientes.GenericQueueClient;
import dto.ElectrodomesticoDTO;
import dto.InfantilDTO;
import dto.ModaDTO;
import dto.MuebleDTO;
import parsers.ArticuloParser;
import transformer.Transformer;
import entities.Articulo;
import entities.Electrodomestico;
import entities.Infantil;
import entities.Moda;
import entities.Mueble;
import entities.PortalConexion;

/**
 * Session Bean implementation class PortalDAO
 */
@Stateless
@LocalBean
public class PortalDAO {

	@PersistenceContext
	EntityManager em;

	private List<PortalConexion> conexiones;
	private static final Logger logger = 
			   Logger.getLogger(PortalDAO.class);
	public PortalDAO() {
		conexiones = new ArrayList<PortalConexion>();
		this.obtenerConexiones();
	}

	@SuppressWarnings("unchecked")
	private void obtenerConexiones() {
		Query q = em.createQuery("select from PortalConexion and active = TRUE");
		conexiones = (List<PortalConexion>) q.getResultList();
	}

	
	public void enviar (Mueble m){
		ArticuloParser parser = new ArticuloParser();
		MuebleDTO mDTO = Transformer.obtenerInstancia().toDTO(m);
		String xml=parser.toXML(mDTO);
		enviar(xml);
	}

	public void enviar (Infantil i){
		ArticuloParser parser = new ArticuloParser();
		InfantilDTO mDTO = Transformer.obtenerInstancia().toDTO(i);
		String xml=parser.toXML(mDTO);
		enviar(xml);
	}

	public void enviar (Electrodomestico e){
		ArticuloParser parser = new ArticuloParser();
		ElectrodomesticoDTO eDTO = Transformer.obtenerInstancia().toDTO(e);
		String xml=parser.toXML(eDTO);
		enviar(xml);
	}
	public void enviar (Moda m){
		ArticuloParser parser = new ArticuloParser();
		ModaDTO mDTO = Transformer.obtenerInstancia().toDTO(m);
		String xml=parser.toXML(mDTO);
		enviar(xml);
	}


	public void enviar(String xml) {
		this.obtenerConexiones();
		String errorMessage = new String();
		for (PortalConexion p : conexiones) {
			GenericQueueClient cliente = new GenericQueueClient(p.getQueueName(), p.getIp(), p.getPuerto(), "user", "pass");
			try {
				cliente.enviar(xml);
				cliente.cerrarConexion();
			} catch (JMSException e) {
				errorMessage = "*** Error enviando xml a jms de Portal IP["+p.getIp()+"] Grupo ["+p.getPortalId()+"]***"; 
				logger.error(errorMessage, e);
			}
		}
	}
}
