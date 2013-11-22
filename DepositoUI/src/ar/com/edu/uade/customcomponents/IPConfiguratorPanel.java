package ar.com.edu.uade.customcomponents;

import java.util.HashMap;

import view.ConnectionView;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ChameleonTheme;

public class IPConfiguratorPanel extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private IpConfigurator ipConfigurator_4;
	//@AutoGenerated
	//private IpConfigurator ipConfigurator_3;
	private MonitorepIpConfigurator ipConfigurator_3;
	@AutoGenerated
	private IpConfigurator ipConfigurator_2;
	@AutoGenerated
	private IpConfigurator ipConfigurator_1;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param portales4 
	 * @param monitores 
	 * @param despachos 
	 * @param portales 
	 */
	public IPConfiguratorPanel(HashMap<Integer, ConnectionView> portales,HashMap<Integer, ConnectionView> despachos, HashMap<Integer, ConnectionView> monitoreo,
			HashMap<Integer, ConnectionView> fabrica) {
		buildMainLayout(portales,despachos, monitoreo, fabrica);
		setCompositionRoot(mainLayout);
		

		// TODO add user code here
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout(HashMap<Integer, ConnectionView> portales, HashMap<Integer, ConnectionView> despachos, 
			HashMap<Integer, ConnectionView> monitoreo, HashMap<Integer, ConnectionView> fabrica) {
		// common part: create layout

		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("930px");
		mainLayout.setHeight("440px");
		// top-level component properties
		setWidth("620px");
		setHeight("440px");
		
		// gridLayout_1
		gridLayout_1 = buildGridLayout_1(portales,despachos, monitoreo, fabrica);
		
		mainLayout.addComponent(gridLayout_1, "top:80.0px;left:38.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1(HashMap<Integer, ConnectionView> portales, HashMap<Integer, ConnectionView> despachos, 
			HashMap<Integer, ConnectionView> monitoreo, HashMap<Integer, ConnectionView> fabrica) {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("-1px");
		gridLayout_1.setHeight("-1px");
		gridLayout_1.setMargin(true);
		gridLayout_1.setColumns(2);
		gridLayout_1.setRows(2);
		
		// ipConfigurator_1
		ipConfigurator_1 = new IpConfigurator();
		ipConfigurator_1.init("Portales", portales);
		ipConfigurator_1.setImmediate(false);
		ipConfigurator_1.setWidth("-1px");
		ipConfigurator_1.setHeight("-1px");
		
		gridLayout_1.addComponent(ipConfigurator_1, 0, 0);
		
		// ipConfigurator_2
		ipConfigurator_2 = new IpConfigurator();
		ipConfigurator_2.init("Despachos", despachos);
		ipConfigurator_2.setImmediate(false);
		ipConfigurator_2.setWidth("-1px");
		ipConfigurator_2.setHeight("-1px");
		gridLayout_1.addComponent(ipConfigurator_2, 1, 0);
		
		// ipConfigurator_3
//		ipConfigurator_3 = new IpConfigurator("Monitoreo", monitoreo);
//		ipConfigurator_3.setImmediate(false);
//		ipConfigurator_3.setWidth("-1px");
//		ipConfigurator_3.setHeight("-1px");
//		gridLayout_1.addComponent(ipConfigurator_3, 0, 1);
		ipConfigurator_3 = new MonitorepIpConfigurator();
		ipConfigurator_3.init("Monitoreo", monitoreo);
		ipConfigurator_3.setImmediate(false);
		ipConfigurator_3.setWidth("-1px");
		ipConfigurator_3.setHeight("-1px");
		gridLayout_1.addComponent(ipConfigurator_3, 0, 1);
		//gridLayout_1.addComponent(ipConfigurator_3, 2, 0);
//		// ipConfigurator_4
//		ipConfigurator_4 = new IpConfigurator();
//		ipConfigurator_4.init("Fabrica", fabrica);
//		ipConfigurator_4.setImmediate(false);
//		ipConfigurator_4.setWidth("-1px");
//		ipConfigurator_4.setHeight("-1px");
//		gridLayout_1.addComponent(ipConfigurator_4, 1, 1);

		
		gridLayout_1.setColumnExpandRatio(0, 1);
		gridLayout_1.setColumnExpandRatio(1, 1);
		gridLayout_1.setRowExpandRatio(0, 1);
		gridLayout_1.setRowExpandRatio(1, 1);
		return gridLayout_1;
	}

}
