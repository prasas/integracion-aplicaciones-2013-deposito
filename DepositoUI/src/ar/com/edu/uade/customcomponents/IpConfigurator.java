package ar.com.edu.uade.customcomponents;

import java.util.ArrayList;
import java.util.HashMap;









import javax.naming.NamingException;

import view.ConnectionView;
import ar.com.edu.uade.ejbfacade.EJBFacade;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import excepctions.BackEndException;




public class IpConfigurator extends CustomComponent {

	private static final long serialVersionUID = -6583013919244415167L;

	EJBFacade facade;
	public class AddNewIPWindow extends Window {
		private static final long serialVersionUID = -1867392910754863989L;
		private TextField ip =  new TextField("IP");	
		private TextField id =  new TextField("ID");
		private String configuredIP;
		private String configuredID;

		public AddNewIPWindow() {
			super("Agregue nueva IP"); // Set window caption
			setDraggable(true);
			
			try {
				facade = EJBFacade.getIntance();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        center();
	        configuredIP = "";
	        setStyleName(ChameleonTheme.WINDOW_OPAQUE);
	        // Some basic content for the window
	        FormLayout content = new FormLayout();
	        content.addComponent(id);
	        content.addComponent(ip);
	        content.setMargin(true);
	        setContent(content);
	        setClosable(true);
	        setResizable(false);
	        setDraggable(true);
	        setModal(true);
	        // Trivial logic for closing the sub-window
	        Button ok = new Button("OK",new Button.ClickListener() {
				private static final long serialVersionUID = 3216294094902350962L;
				public void buttonClick(ClickEvent event) {
					configuredIP = ip.getValue();
					configuredID = id.getValue();
					addNewIP(configuredID, configuredIP);
	                close(); // Close the sub-window
	                
	            }
	        });
	        content.addComponent(ok);
	        
	    }
	}

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private Button button_1;
	private Button button_2;
	String grupo;
	@AutoGenerated
	private Panel panel_2;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private OptionGroup optionGroup_2;
	private AddNewIPWindow newIPConfiguratorWindow;
	private HashMap<String, ConnectionView> mappedIPs;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public IpConfigurator() {
		
	}
	public void init(String group, HashMap<Integer, ConnectionView> actualSet){
		addStyleName("reindeer");
		mappedIPs = new HashMap<String, ConnectionView>();
		buildMainLayout();
		setCompositionRoot(mainLayout);
		configureGroupBox(actualSet);
		configureLabel(group);
		this.grupo = group.toUpperCase();
		newIPConfiguratorWindow = new AddNewIPWindow();
		newIPConfiguratorWindow.setStyleName(Runo.WINDOW_DIALOG);
		setImmediate(true);
		// TODO add user code here
	}
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("280px");
		mainLayout.setHeight("180px");
		
		// top-level component properties
		setWidth("280px");
		setHeight("180px");
		
		// panel_2
		panel_2 = buildPanel_2();
		mainLayout
				.addComponent(panel_2, "top:35.0px;right:60.0px;left:20.0px;");
		
		// button_1
		button_1 = new Button("+",new Button.ClickListener() {
			private static final long serialVersionUID = 3809094252530243514L;
			@Override
			public void buttonClick(ClickEvent event) {
		         UI.getCurrent().addWindow(newIPConfiguratorWindow);
		         
			}
		});
		button_2 = new Button("Aceptar",new Button.ClickListener() {
			private static final long serialVersionUID = 3809094252530243514L;
			@Override
			public void buttonClick(ClickEvent event) {
		         
				ArrayList<ConnectionView> activas = new ArrayList<ConnectionView>();	
				String nuevo = optionGroup_2.getValue().toString().substring(1, 
						optionGroup_2.getValue().toString().length()-1);
				
		        if (nuevo.length() > 0) {
					String[] list = nuevo.split(",");
			        for (int i = 0; i < list.length; i++) {
						String string = list[i];					
						activas.add(mappedIPs.get(string.trim()));					
						System.out.println(mappedIPs.get(string.trim()));
						}
		        }
		        if (mappedIPs.size()==0) {
		        	Notification.show("No IP's Selected, select at least one", Type.WARNING_MESSAGE);
		        	return;
		        }
		        try {
			        if (grupo.equalsIgnoreCase("portales")){
								facade.savePortalesConnection(activas);
								Notification.show("Ip Portales Creadas", Type.HUMANIZED_MESSAGE);
	
					}
					if (grupo.equalsIgnoreCase("despachos")){
							facade.saveDespachosConnection(activas);
							Notification.show("Ip Despachos Creadas", Type.HUMANIZED_MESSAGE);
					}
	
					if (grupo.equalsIgnoreCase("Fabrica")){
							facade.saveFabricaConnection(activas);
							Notification.show("Ip Fabrica Creadas", Type.HUMANIZED_MESSAGE);
					}
					 //UI.getCurrent().getNavigator().navigateTo("/creararticulo");
				}catch (BackEndException e){
					Notification.show("IP's no pueden ser salvadas", Type.ERROR_MESSAGE);
				}
				
				
			}
		});
		button_2.setCaption("Aceptar");
		button_2.setImmediate(false);
		button_2.setDescription("Accept Changes");
		button_2.setWidth("-1px");
		button_2.setHeight("-1px");
		
		button_1.setCaption("+");
		button_1.setImmediate(false);
		button_1.setDescription("Add new ip");
		button_1.setWidth("-1px");
		button_1.setHeight("-1px");
		mainLayout.addComponent(button_1, "top:154.0px;left:170.0px;");
		
		mainLayout.addComponent(button_2, "top:154.0px;left:20.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setImmediate(false);
		panel_2.setWidth("100.0%");
		panel_2.setHeight("120px");
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		panel_2.setContent(verticalLayout_2);
		
		return panel_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("95.87%");
		verticalLayout_2.setHeight("100.56%");
		verticalLayout_2.setMargin(false);
		
		// optionGroup_2
		optionGroup_2 = new OptionGroup();
		
		optionGroup_2.setImmediate(false);
		optionGroup_2.setWidth("-1px");
		optionGroup_2.setHeight("-1px");
		
		
		
		verticalLayout_2.addComponent(optionGroup_2);
		return verticalLayout_2;
	}

	private void configureGroupBox(HashMap<Integer, ConnectionView> actualSet) {
		optionGroup_2.setMultiSelect(true);

		for (Integer key : actualSet.keySet()) {
			ConnectionView connectionView = actualSet.get(key);
			String groupValue = key + "-" + connectionView.getIp();
			optionGroup_2.addItem(groupValue);

			if (connectionView.isActive()) {
				optionGroup_2.select(groupValue);
			}
			
			mappedIPs.put(groupValue, connectionView);
		}
	}

	private void configureLabel(String label){
		// label_1
		label_1 = new Label(label);
		label_1.setImmediate(false);
		label_1.setWidth("140px");
		label_1.setHeight("-1px");
		mainLayout.addComponent(label_1, "top:10.0px;left:20.0px;");		
	}
	 protected void addNewIP(String newID, String newIP){
		 
		 String groupValue =newID+"-"+newIP;
		 optionGroup_2.addItem(groupValue);
		 
		 mappedIPs.put(groupValue,new ConnectionView(Integer.parseInt(newID),false,newIP,false));
		 
		 System.out.println(mappedIPs.get(groupValue));
		 
	
	 }
	 

}
