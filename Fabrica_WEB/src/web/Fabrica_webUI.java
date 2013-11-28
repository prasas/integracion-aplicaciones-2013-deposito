package web;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

import view.SolicitudArticulosItemView;
import view.SolicitudCompraView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ejbfacade.EJBFacade;

@SuppressWarnings("serial")
@Theme("fabrica_web")
public class Fabrica_webUI extends UI {

	@WebServlet(value = {"/adm/*", "/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Fabrica_webUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		Table table= new Table("Solicitudes de compra");
		
		table = armarTabla(table);
		
		layout.addComponent(table);
		
		Button button = crearBoton(layout, table);
		layout.addComponent(button);
	}
	
	private Table armarTabla(Table table){

		List<SolicitudCompraView> listaView=null;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		/* Define the names and data types of columns.
		 * The "default value" parameter is meaningless here. */
		table.addContainerProperty("FechaCreacion", String.class,  null);
		table.addContainerProperty("FechaFin", String.class,  null);
		table.addContainerProperty("Codigo",  Long.class,  null);
		table.addContainerProperty("Contenido", TextArea.class, null);
		table.addContainerProperty("Enviar", CheckBox.class,  null);
		
		try {
			listaView=EJBFacade.getIntance().getSolicitudesDeCompra();
		} catch (NamingException e) {
			// TODO MostrarError de que no me pude conectar al ejb.
			e.printStackTrace();
			table.addItem(new Object[] {
				    "2013/11/26","2013/11/26","1",new TextField("ERROR DE CONEXION AL EJB"), new CheckBox("",false)}, new Integer(1));
			return table;
		}
		
		int i=0;

		for (SolicitudCompraView scv : listaView){
			String textoArts="";
			String textoDate="";
			String textoFechaFin="";
			CheckBox check= new CheckBox("",false);
			TextArea articulos= new TextArea("articulos");
			articulos.setWidth("150");
			
			boolean completa=false;
			int j=0; 
			
			if (scv.getEstado().equals(SolicitudCompraView.COMPLETA)) {
				check.setEnabled(false);
				check.setVisible(false);
				completa=true;
			}
			
			if (scv.getDate()!=null) {
				textoDate=formatoDelTexto.format(scv.getDate());
			}
			
			if (scv.getFechaFin()!=null) {
				textoFechaFin=formatoDelTexto.format(scv.getFechaFin());	
			}

			for (SolicitudArticulosItemView item: scv.getArticulos()){
				if (j!=0){ textoArts=textoArts + "\n";} 
				textoArts=textoArts + " Art:" + item.getArticulo().getCodigo() + "|" + item.getArticulo().getNombre() + "|" + item.getCantidad() + "u." ;
				j++;
			}
			
			if (completa) {
				textoArts=textoArts + "\n****COMPLETADA*****";
				j++;
			}
			articulos.setValue(textoArts);
			articulos.setRows(j);
			table.addItem(new Object[] {
					textoDate, textoFechaFin,scv.getCodigoSolicitud(),articulos, check}, new Integer(i));
			
			i++;
		}
		table.setPageLength(i);
		return table;
	}

	private Button crearBoton(final VerticalLayout layout, final Table tabla){
		Button button=new Button("Enviar Solicitudes");
		 button.addClickListener(new Button.ClickListener() {
		 public void buttonClick(ClickEvent event) {
			 layout.addComponent(new Label("Enviado"));
			 enviarSolicitudes(tabla);
			 tabla.removeAllItems();
			 armarTabla(tabla);
		 }
		 });
		return button;
	}
	
	protected void enviarSolicitudes(Table table) {

		List<Long> lista=new ArrayList<Long>();
		

		// Iterate over the item identifiers of the table.
		for (Iterator i = table.getItemIds().iterator(); i.hasNext();) {
		    // Get the current item identifier, which is an integer.
		    int iid = (Integer) i.next();
		    
		    // Now get the actual item from the table.
		    Item item = table.getItem(iid);
		    
		    // And now we can get to the actual checkbox object.
		    CheckBox check = (CheckBox)
		            (item.getItemProperty("Enviar").getValue());
		    
		    // If the checkbox is selected.
		    if (check.getValue() == true) {

		    	lista.add((Long) item.getItemProperty("Codigo").getValue());
		    	
		    }
		}
		 
		try {
			EJBFacade.getIntance().entregarCompras(lista);
		} catch (NamingException e) {
			// TODO Mostrar error de comunicacion
			e.printStackTrace();
		}
		
	}					 

}

