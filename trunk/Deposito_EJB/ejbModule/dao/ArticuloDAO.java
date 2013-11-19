package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import entities.Articulo;
import entities.Electrodomestico;
import entities.Infantil;
import entities.Moda;
import entities.Mueble;

@Stateless
@LocalBean
public class ArticuloDAO {
	
	@PersistenceContext
	EntityManager em;
	private static final Logger logger = 
			   Logger.getLogger(ArticuloDAO.class);

	public ArticuloDAO() {
	}
	
	public Articulo find(Long cod){
		Articulo a=null;
		try{
			
			a= em.find(Articulo.class, cod);
			logger.info("Find articulo persistido con c�digo: ["+cod+"] ");
		}catch(Exception e)
		{
			logger.error("Error buscando Articulo codigo ["+cod+"]");
			logger.error(e);
		}
		return a;
	}
	public List<Articulo> findAll(){
		List<Articulo> al = new ArrayList<Articulo>();
		try{
			Query q = em.createNativeQuery("from Articulo");
			al =(List<Articulo> ) q.getResultList();
			logger.info("Get All Articulos ");
		}catch(Exception e)
		{
			logger.error("Error on Get All Articulos");
			logger.error(e);
		}
		return al;
	}
	public void guardarArticulo(Articulo a){
		try{
			System.out.println(a==null);
			System.out.println(em==null);
			em.persist(a);
			logger.info("Articuo codigo ["+a.getCodigo()+"] persistido");
		}catch(Exception e)
		{
			logger.error("Error persistiendo Articulo codigo ["+a.getCodigo()+"]");
			logger.error(e);
		}
	}
	
	public void actualizarArticulo(Articulo a){
		try{
			
			em.merge(a);
			logger.info("Articuo codigo ["+a.getCodigo()+"] persistido");
		}catch(Exception e)
		{
			logger.error("Error persistiendo Articulo codigo ["+a.getCodigo()+"]");
			logger.error(e);
		}
	}


	@SuppressWarnings("unchecked")
	public List<Electrodomestico> findAllElectrodomesticos() {
		List<Electrodomestico> al = new ArrayList<Electrodomestico>();
		try{
			Query q = em.createNativeQuery("from Electrodomestico");
			al =(List<Electrodomestico> ) q.getResultList();
			logger.info("Get All Electrodomestico ");
		}catch(Exception e)
		{
			logger.error("Error on Get All Electrodomestico");
			logger.error(e);
		}
		return al;
	}
	@SuppressWarnings("unchecked")
	public List<Moda> findAllByModa() {
		List<Moda> al = new ArrayList<Moda>();
		try{
			Query q = em.createNativeQuery("from Moda");
			al =(List<Moda> ) q.getResultList();
			logger.info("Get All Moda ");
		}catch(Exception e)
		{
			logger.error("Error on Get All Moda");
			logger.error(e);
		}
		return al;
	}
	@SuppressWarnings("unchecked")
	public List<Infantil> findAllInfantil() {
		List<Infantil> al = new ArrayList<Infantil>();
		try{
			Query q = em.createNativeQuery("from Infantil");
			al =(List<Infantil> ) q.getResultList();
			logger.info("Get All Articulos ");
		}catch(Exception e)
		{
			logger.error("Error on Get All Infantil");
			logger.error(e);
		}
		return al;
	}
	@SuppressWarnings("unchecked")
	public List<Mueble> findAllMuebles() {
		List<Mueble> al = new ArrayList<Mueble>();
		try{
			Query q = em.createNativeQuery("from Mueble");
			al =(List<Mueble> ) q.getResultList();
			logger.info("Get All Mueble ");
		}catch(Exception e)
		{
			logger.error("Error on Get All Mueble");
			logger.error(e);
		}
		return al;
	}
}
