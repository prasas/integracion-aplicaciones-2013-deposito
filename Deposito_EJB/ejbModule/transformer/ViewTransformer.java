package transformer;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import view.ArticuloView;
import view.ConnectionView;
import view.ElectrodomesticoView;
import view.InfantilView;
import view.ModaView;
import view.MuebleView;
import view.SolicitudArticulosItemView;
import view.SolicitudArticulosView;
import view.SolicitudCompraView;
import dao.ArticuloDAO;
import dao.SolicitudArticulosDAO;
import entities.Articulo;
import entities.DespachoConexion;
import entities.Electrodomestico;
import entities.Infantil;
import entities.ItemSolicitudCompra;
import entities.Moda;
import entities.MonitoreoConexion;
import entities.Mueble;
import entities.PortalConexion;
import entities.SolicitudArticulos;
import entities.SolicitudArticulosItem;
import entities.SolicitudCompra;
import excepctions.BackEndException;

@Stateless
@LocalBean
public class ViewTransformer {
	@EJB
	ArticuloDAO articuloDAO;

	@EJB
	SolicitudArticulosDAO solicitudArticulosDAO;

	public ViewTransformer() {

	}

	public Articulo converToClass(ArticuloView av) {
		Articulo a = null;
		String tipo =  av.getTipo();
		
		if(tipo.equalsIgnoreCase("electrodomestico"))
			a = new Electrodomestico();
		if(tipo.equalsIgnoreCase("moda"))
			a = new Moda();
		if(tipo.equalsIgnoreCase("mueble"))
			a = new Mueble();
		if(tipo.equalsIgnoreCase("infantil"))
			a = new Infantil();
		
		a.setCodigo(av.getCodigo());
		a.setCodigoDeposito(av.getCodigoDeposito());
		a.setDescripcion(av.getDescripcion());
		a.setFoto(av.getFoto());
		a.setMarca(av.getMarca());
		a.setNombre(av.getNombre());
		a.setOrigen(av.getOrigen());
		a.setPrecio(av.getPrecio());
		return a;
	}

	public Electrodomestico converToClass(ElectrodomesticoView ev) {
		Electrodomestico e = new Electrodomestico();
		e.setCodigo(ev.getCodigo());
		e.setCodigoDeposito(ev.getCodigoDeposito());
		e.setDescripcion(ev.getDescripcion());
		e.setFichaTecnica(ev.getFichaTecnica());
		e.setFoto(ev.getFoto());
		e.setMarca(ev.getMarca());
		e.setNombre(ev.getNombre());
		e.setOrigen(ev.getOrigen());
		e.setPrecio(ev.getPrecio());
		return e;
	}

	public Infantil converToClass(InfantilView iv) {
		Infantil i = new Infantil();
		i.setCodigo(iv.getCodigo());
		i.setCodigoDeposito(iv.getCodigoDeposito());
		i.setDescripcion(iv.getDescripcion());
		i.setEdadRecomendada(iv.getEdadRecomendada());
		i.setFoto(iv.getFoto());
		i.setMarca(iv.getMarca());
		i.setNombre(iv.getNombre());
		i.setOrigen(iv.getOrigen());
		i.setPrecio(iv.getPrecio());
		return i;
	}

	public Moda converToClass(ModaView mv) {
		Moda m = new Moda();
		m.setCodigo(mv.getCodigo());
		m.setCodigoDeposito(mv.getCodigoDeposito());
		m.setColor(mv.getColor());
		m.setDescripcion(mv.getDescripcion());
		m.setFoto(mv.getFoto());
		m.setMarca(mv.getMarca());
		m.setNombre(mv.getNombre());
		m.setOrigen(mv.getOrigen());
		m.setPrecio(mv.getPrecio());
		m.setTalle(mv.getTalle());
		return m;
	}

	public Mueble converToClass(MuebleView mv) {
		Mueble m = new Mueble();
		m.setCodigo(mv.getCodigo());
		m.setCodigoDeposito(mv.getCodigoDeposito());
		m.setDescripcion(mv.getDescripcion());
		m.setFoto(mv.getFoto());
		m.setMarca(mv.getMarca());
		m.setMaterial(mv.getMaterial());
		m.setNombre(mv.getNombre());
		m.setOrigen(mv.getOrigen());
		m.setPrecio(mv.getPrecio());
		return m;
	}

	private void setArticuloView(Articulo a, ArticuloView view) {
		view.setCodigo(a.getCodigo());
		view.setCodigoDeposito(6L);
		view.setDescripcion(a.getDescripcion());
		view.setFoto(a.getFoto());
		view.setMarca(a.getMarca());
		view.setNombre(a.getNombre());
		view.setOrigen(a.getOrigen());
		view.setPrecio(a.getPrecio());
		view.setStock(a.getStock());
	}

	public MuebleView toView(Mueble m) {
		MuebleView view = new MuebleView();
		setArticuloView(m, view);
		view.setMaterial(m.getMaterial());
		return view;
	}

	public InfantilView toView(Infantil i) {
		InfantilView view = new InfantilView();
		setArticuloView(i, view);
		view.setEdadRecomendada(i.getEdadRecomendada());
		return view;
	}

	public ModaView toView(Moda m) {
		ModaView view = new ModaView();
		setArticuloView(m, view);
		view.setColor(m.getColor());
		view.setTalle(m.getTalle());
		return view;
	}

	public ElectrodomesticoView toView(Electrodomestico e) {
		ElectrodomesticoView view = new ElectrodomesticoView();
		setArticuloView(e, view);
		view.setFichaTecnica(e.getFichaTecnica());
		return view;
	}

	public SolicitudCompraView toView(SolicitudCompra solicitud) {
		SolicitudCompraView view = new SolicitudCompraView();
		view.setCodigoSolicitud(solicitud.getCodigo());
		if (solicitud.getFechaFin() == null) {
			// TODO: MF set no entregado.
		} else {
			// TODO: MF set entregado.
		}

		for (ItemSolicitudCompra i : solicitud.getArticulos()) {
			view.getArticulos().add(toView(i));
		}
		return view;
	}

	public SolicitudCompra converToClass(SolicitudCompraView scv) throws BackEndException {
		SolicitudCompra solicitud = new SolicitudCompra();
		solicitud.setCodigo(scv.getCodigoSolicitud());
		solicitud.setFechaInicio(scv.getDate());
		scv.setDate(solicitud.getFechaInicio());
		if (solicitud.isCompletada()){
			scv.setEstado(SolicitudCompraView.COMPLETA);
		} else {
			scv.setEstado(SolicitudCompraView.PENDIENTE);
		}
		scv.setFechaFin(solicitud.getFechaFin());

		for (SolicitudArticulosItemView item : scv.getArticulos()) {
			solicitud.getArticulos().add(this.converToClass(item));
		}

		return solicitud;
	}

	private ItemSolicitudCompra converToClass(SolicitudArticulosItemView iscv) throws BackEndException {
		ItemSolicitudCompra item = new ItemSolicitudCompra();
		item.setArticulo(articuloDAO.find(iscv.getArticulo().getCodigo()));
		item.setCantidad(iscv.getCantidad());
		return item;
	}

	public SolicitudArticulos converToClass(SolicitudArticulosView scv) throws BackEndException {
		SolicitudArticulos solicitud = new SolicitudArticulos();
		solicitud = solicitudArticulosDAO.buscarSolicitud(scv.getCodigoSolicitud());
		return solicitud;
	}

	private SolicitudArticulosItem converToSolicitudArticulosItem(SolicitudArticulosItemView iscv) throws BackEndException {
		SolicitudArticulosItem item = new SolicitudArticulosItem();
		item.setArticulo(articuloDAO.find(iscv.getArticulo().getCodigo()));
		item.setCantidad(iscv.getCantidad());
		return item;
	}

	private SolicitudArticulosItemView toView(ItemSolicitudCompra item) {

		Articulo a = item.getArticulo();
		ArticuloView av = actualizarArticuloView(a);

		SolicitudArticulosItemView view = new SolicitudArticulosItemView(av, item.getCantidad());
		view.setTotalSolicitado(item.getCantidadSolicitada());
		return view;
	}

	private ArticuloView actualizarArticuloView(Articulo a) {
		ArticuloView av = null;

		if (a.getClass() == Infantil.class) {
			av = toView((Infantil) a);
		} else if (a.getClass() == Electrodomestico.class) {
			av = toView((Electrodomestico) a);
		} else if (a.getClass() == Mueble.class) {
			av = toView((Mueble) a);
		} else if (a.getClass() == Moda.class) {
			av = toView((Moda) a);
		}
		return av;
	}

	public DespachoConexion despachoToClass(ConnectionView c) {
		DespachoConexion conn = new DespachoConexion();
		conn.setDespachoId(c.getModuleId());
		conn.setIp(c.getIp());
		conn.setActive(true);
		conn.setUsuario("despacho");
		conn.setPassword("despacho123");
		conn.setPuerto("4447");
		conn.setQueueName("jms/queue/nuevosArticulos");
		conn.setRestPath("/despacho_web/rest/despachador/recibirArticulos");
		conn.setRestPort("8080");
		return conn;
	}

	public PortalConexion portalToClass(ConnectionView c) {
		PortalConexion conn = new PortalConexion();
		conn.setPortalId(c.getModuleId());
		conn.setIp(c.getIp());
		conn.setPuerto("4447");
		conn.setActive(true);
		conn.setQueueName("jms/queue/deposito");
		conn.setUsuario("deposito");
		conn.setPassword("deposito123");

		return conn;
	}

	public MonitoreoConexion monitoreoToClass(ConnectionView c) {
		MonitoreoConexion conn = new MonitoreoConexion();
		conn.setMonitoreoId(c.getModuleId());
		conn.setIp(c.getIp());
		conn.setPuerto("4447");
		conn.setActive(true);
		conn.setSyncronico(c.isSyncronic());
		conn.setUsuario("log");
		conn.setPassword("log1234");
		conn.setQueueName("jms/queue/log");
		conn.setWsPath("/LogisticaMonitoreo/LogisticaMonitoreoWS");
		conn.setWsPuerto("8080");
		return conn;
	}

	public ConnectionView toView(PortalConexion c) {
		ConnectionView view = new ConnectionView();
		view.setActive(true);
		view.setIp(c.getIp());
		view.setModuleId(c.getPortalId());
		return view;
	}

	public ConnectionView toView(DespachoConexion c) {
		ConnectionView view = new ConnectionView();
		view.setActive(true);
		view.setIp(c.getIp());
		view.setModuleId(c.getDespachoId());
		return view;
	}

	public ConnectionView toView(MonitoreoConexion c) {
		ConnectionView view = new ConnectionView();
		view.setActive(true);
		view.setIp(c.getIp());
		view.setModuleId(c.getMonitoreoId());
		view.setSyncronic(c.isSyncronico());
		return view;
	}

	public SolicitudArticulosView toView(SolicitudArticulos solicitud) {

		SolicitudArticulosView view = new SolicitudArticulosView();

		view.setCodigoSolicitud(solicitud.getSolicitudId());
		view.setDate(solicitud.getFechaInicio());
		view.setIdModulo(solicitud.getModuloId());
		view.setSelectable(true);

		ArrayList<SolicitudArticulosItemView> itemsView = new ArrayList<SolicitudArticulosItemView>();

		for (SolicitudArticulosItem solicitudItem : solicitud.getItems()) {

			ArticuloView articuloView = actualizarArticuloView(solicitudItem.getArticulo());
			SolicitudArticulosItemView itemView = new SolicitudArticulosItemView(articuloView, solicitudItem.getCantidad());
			itemView.setTotalSolicitado(0);

			itemsView.add(itemView);
		}

		view.setItems(itemsView);

		return view;
	}
}
