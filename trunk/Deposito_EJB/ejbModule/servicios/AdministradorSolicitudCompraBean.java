package servicios;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.FabricaDAO;
import dao.SolicitudCompraDAO;
import dto.SolicitudCompraDTO;
import entities.SolicitudCompra;

/**
 * Session Bean implementation class AdministradorSolicitudCompraBean
 */
@Stateless
@LocalBean
public class AdministradorSolicitudCompraBean implements
		AdministradorSolicitudCompra {

	@EJB
	private FabricaDAO fabricaDAO;

	@EJB
	private SolicitudCompraDAO solicitudCompraDAO;

	/**
	 * Default constructor.
	 */
	public AdministradorSolicitudCompraBean() {
	}

	@Override
	public void crear(SolicitudCompraDTO compraDTO) {

		/*
		 * get entity, validar, enviar a fabrica. Si falla, rollback y log
		 */

		try {
			SolicitudCompra compra = getEntity(compraDTO);

			// TODO AR: Validar entity

			solicitudCompraDAO.persist(compra);

			// TODO AR: recepcion de respuesta?
			fabricaDAO.enviar(compra);
		} catch (Exception e) {
			// TODO AR: log de errores y rollback de TODO
			e.printStackTrace();
		}

	}

	private SolicitudCompra getEntity(SolicitudCompraDTO compraDTO) {
		// TODO AR: crear entity desde dto.
		// los articulos debe recuperarlos
		return null;
	}

	@Override
	public void recibir(SolicitudCompraDTO compraDTO) {
		/*
		 * getEntity validar actualizar el stock de cada articulo marcar la
		 * solicitud de compra com recibida
		 */
		SolicitudCompra compra = getEntity(compraDTO);

		// for (Articulo art : compra.getArticulos()) {
		// /*
		// * buscar entity stock stock
		// * actualizar stock
		// * validar que se persista
		// * */
		// }

		compra.setCompletada(true);
		solicitudCompraDAO.merge(compra);

	}

}
