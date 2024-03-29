package sessionBeans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;















import entities.SolicitudCompra;
import excepctions.BackEndException;
import servicios.AdministradorArticulosBean;
import servicios.AdministradorConecctionsBean;
import servicios.AdministradorSolicitudCompraBean;
import transformer.ViewTransformer;
import view.*;
@Stateless
public class DepositoFacadeBean implements DepositoFacade {

	@EJB
	AdministradorArticulosBean admin ;
	@EJB
	AdministradorConecctionsBean connAdmin ;
	@EJB
	AdministradorSolicitudCompraBean admSC;
	
	@EJB 
	ViewTransformer vt;
	
	private static final Logger logger = 
			   Logger.getLogger(DepositoFacadeBean.class.getName());
	public DepositoFacadeBean(){}

	@Override
	public void altaElectrodomestico(ElectrodomesticoView dto) {
		logger.info("*** Alta Electrodomestico  ***" );
		admin.guardarElectrodomestico(dto);
	}

	@Override
	public void altaModa(ModaView m) {
		logger.info("*** Alta Articulo de moda  ***" );
		admin.guardarModa(m);
	}

	@Override
	public void altaMueble(MuebleView m) {
		logger.info("*** Alta Mueble ***" );
		admin.guardarMueble(m);
	}

	@Override
	public void altaInfatil(InfantilView i) {
		logger.info("*** Alta Infantil  ***" );
		admin.guardarInfantil(i);
	}

	@Override
	public void actualizarStock(ArticuloView dto, long stock) {
		logger.info("*** Actualizar Stock  ***" );
		admin.actualizarStock(dto, stock);
	}
	
	@Override
	public ArrayList<SolicitudCompraView> getSolicitudesCompra() {
		return admin.getSolicitudesCompra();
	}
	
//	@Override
//	public ArrayList<SolicitudArticulosView> getSolicitudesArticulos() {

//	}
	
	@Override
	public ArrayList<ArticuloView> getArticulos() throws BackEndException {
		return admin.getArticulos();
	}
	
	@Override
	public ArrayList<ConnectionView> getFabricasConnection() {
		return null;
	}
	@Override
	public ArrayList<ConnectionView> getMonitoreosConnection() throws BackEndException {
		return connAdmin.getMonitoreos();
	}
	
	@Override
	public ArrayList<ConnectionView> getDespachosConnection() throws BackEndException {
		return connAdmin.getDespachos();
	}
	
	@Override
	public ConnectionView getDespachoConnection(int idModulo) {
		return connAdmin.getDespacho(idModulo);
	}
	@Override
	public ArrayList<ConnectionView> getPortalesConnection() throws BackEndException {
		
		return connAdmin.getPortales();
	}
	@Override
	public void savePortalesConnection(ArrayList<ConnectionView> activas) throws BackEndException {
		connAdmin.savePortalesConnection(activas);
		
	}

	@Override
	public void saveDespachosConnection(ArrayList<ConnectionView> activas) throws BackEndException {
		connAdmin.saveDespachosConnection(activas);
		
	}
	public void crearSolicitudCompra(SolicitudCompraView compra) throws BackEndException{

		admSC.crear(compra);	
	}
	
	public SolicitudCompraView getRecomendacionCompra() throws BackEndException{
		SolicitudCompra SC=admSC.getRecomendacionCompra();
		
		return vt.toView(SC);
	}
	
	@Override
	public void saveFabricasConnection(ArrayList<ConnectionView> activas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMonitoreoConnection(ArrayList<ConnectionView> activas) throws BackEndException {
		connAdmin.saveMonitoreoConnection(activas);
		
	}
}
