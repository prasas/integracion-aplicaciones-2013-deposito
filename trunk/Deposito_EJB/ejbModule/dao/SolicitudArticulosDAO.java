package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entities.Articulo;
import entities.SolicitudArticulos;

/**
 * Session Bean implementation class SolicitudArticulosDAO
 */
@Stateless
@LocalBean
public class SolicitudArticulosDAO {

    /**
     * Default constructor. 
     */
    public SolicitudArticulosDAO() {
    }

	public void persist(SolicitudArticulos solicitud) {
		// TODO AR - Persistir entidad, manejo de errores
		
	}

	public Articulo buscarArticulo(String codigo) {
		// TODO AR: Buscar el acticulo dado
		return null;
	}

}
