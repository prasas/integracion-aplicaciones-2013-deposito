package ar.com.edu.uade.ejbfacade;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import sessionBeans.DepositoFacade;
import sessionBeans.DepositoFacadeBean;

import view.ArticuloView;
import view.ConnectionView;
import view.ElectrodomesticoView;
import view.InfantilView;
import view.ModaView;
import view.MuebleView;
import view.SolicitudArticulosView;
import view.SolicitudCompraView;



public class EJBFacade {

	private static final Logger logger = 
			   Logger.getLogger(EJBFacade.class);
	static EJBFacade instance =null;
	@EJB
	DepositoFacade systemFacade = new DepositoFacadeBean();
	private EJBFacade() throws NamingException{

	}
	public static EJBFacade getIntance() throws NamingException{
		if(instance==null)
			instance= new EJBFacade();
		return instance;
	}
	public ArrayList<ConnectionView> getPortalConections(){
		
		ArrayList<ConnectionView> retorno = systemFacade.getPortalesConnection();
		return retorno;
		
	}
	public ArrayList<ConnectionView> getDespachoConnection(){
		ArrayList<ConnectionView> retorno = systemFacade.getDespachosConnection();
		return retorno;
	}
	public ArrayList<ConnectionView> getMonitoreoConnection(){
		ArrayList<ConnectionView> retorno = systemFacade.getMonitoreosConnection();
		return retorno;
	}
	public ArrayList<ConnectionView> getFabricaConnection(){
		ArrayList<ConnectionView> retorno = systemFacade.getFabricasConnection();
		return retorno;
	}
	public ArrayList<SolicitudArticulosView> getSolicitudesDeArticulos(){
		return systemFacade.getSolicitudesArticulos();
	}
	public ArrayList<SolicitudCompraView> getSolicitudesDeCompra(){		
	return systemFacade.getSolicitudesCompra();
		
	}
	public Collection<? extends ArticuloView> getAllArticulos() {
		ArrayList<ArticuloView> articulos =  new ArrayList<ArticuloView>();
			return articulos;
	}
	public void altaElectrodomestico(ElectrodomesticoView e){
		systemFacade.altaElectrodomestico(e);
	}

	public void altaModa(ModaView m) {
		systemFacade.altaModa(m);
	}


	public void altaMueble(MuebleView m) {
		systemFacade.altaMueble(m);
	}


	public void altaInfatil(InfantilView i) {
		systemFacade.altaInfatil(i);
	}
	public void savePortalesConnection(ArrayList<ConnectionView> activas){
		systemFacade.savePortalesConnection(activas);
	}
	public void saveMonitoreosConnection(ArrayList<ConnectionView> activas){
		systemFacade.saveMonitoreoConnection(activas);
	}
	public void saveDespachosConnection(ArrayList<ConnectionView> activas){
		systemFacade.saveDespachosConnection(activas);
	}
	public void saveFabricaConnection(ArrayList<ConnectionView> activas){
		systemFacade.savePortalesConnection(activas);
	}

}
