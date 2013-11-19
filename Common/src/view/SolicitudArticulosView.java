package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SolicitudArticulosView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -818101022335386849L;
	private ArrayList<SolicitudArticulosItemView> items = new ArrayList<SolicitudArticulosItemView>();
	private int idModulo;
	private Date date;
	private long codigoSolicitud;

	public void setItems(ArrayList<SolicitudArticulosItemView> items) {
		this.items = items;
	}
	public void addItemSolicitudArticulos(SolicitudArticulosItemView item){
		items.add(item);
	}

	public long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getCodigoSolicitud() {
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public ArrayList<SolicitudArticulosItemView> getArticulos() {
		return items;
	}
	public ArrayList<SolicitudArticulosItemView> getItems() {
		return items;
	}

}
