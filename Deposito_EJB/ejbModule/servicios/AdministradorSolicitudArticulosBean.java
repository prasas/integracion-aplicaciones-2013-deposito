package servicios;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.ArticuloDAO;
import dao.SolicitudArticulosDAO;
import dto.SolicitudArticuloItemDTO;
import dto.SolicitudArticulosDTO;
import dto.SolicitudCompraDTO;
import entities.SolicitudArticulos;
import entities.SolicitudArticulosItem;

/**
 * Session Bean implementation class AdministradorSolicitudArticulosBean
 */
@Stateless
@LocalBean
public class AdministradorSolicitudArticulosBean implements AdministradorSolicitudArticulos {

	@EJB
	private SolicitudArticulosDAO dao;

	@EJB
	private ArticuloDAO articuloDao;

	/**
	 * Default constructor.
	 */
	public AdministradorSolicitudArticulosBean() {
	}

	@Override
	public void recibirSolicitudArticulos(SolicitudArticulosDTO solicitud) {

		// TODO AR: Validar que los articulos existan en la base, de lo
		// contrario error? Tener en cuenta otro tipo de validaciones

		SolicitudArticulos sa = getEntity(solicitud);

		sa.setCumplida(false);
		dao.persist(sa);
	}

	private SolicitudArticulos getEntity(SolicitudArticulosDTO solicitud) {
		// TODO AR - crear entity desde dto

		SolicitudArticulos solicitudEntity = new SolicitudArticulos();
		solicitudEntity.setModuloId(solicitud.getIdSolicitud());

		for (SolicitudArticuloItemDTO item : solicitud.getLista()) {

			SolicitudArticulosItem itemEntity = new SolicitudArticulosItem();
			itemEntity.setArticulo(articuloDao.find(Long.parseLong(item.getCodigo())));
			itemEntity.setCantidad(item.getCantidad());
			solicitudEntity.getItems().add(itemEntity);
		}

		return solicitudEntity;
	}
	
	@Override
	//envia los articulos a despacho
	public void enviarArticulos(SolicitudCompraDTO compraDTO) {
		try {
//			SolicitudCompra entity = getEntity(compraDTO);

			// TODO AR: Validar entity

			// TODO AR: actualizar objeto y stock
//			solicitudCompraDAO.merge(entity);

//			fabricaDAO.enviar(entity);

			// TODO AR: recepcion de respuesta?

		} catch (Exception e) {
			// TODO AR: log de errores y rollback de TODO
			e.printStackTrace();
		}

	}


}
