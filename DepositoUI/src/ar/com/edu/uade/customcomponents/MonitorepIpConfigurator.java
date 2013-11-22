package ar.com.edu.uade.customcomponents;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.naming.NamingException;

import view.ConnectionView;
import ar.com.edu.uade.ejbfacade.EJBFacade;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;


public class MonitorepIpConfigurator extends CustomComponent {

	private static final long serialVersionUID = -6583013919244415167L;
	EJBFacade facade;
	public class AddNewIPWindow extends Window {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1917252061443099391L;
		private TextField ip =  new TextField("IP");	
		private TextField id =  new TextField("ID");
		final NativeSelect selectSyncro = new NativeSelect("Tipo Comunicacion");
		private String configuredIP;
		private String configuredID;
		private boolean synchronic;
		
		public AddNewIPWindow() {
			super("Agregue nueva IP"); // Set window caption
	        center();
	        
	        try {
				facade = EJBFacade.getIntance();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        configuredIP = "";
	        selectSyncro.addItem("Sincronico");
	        selectSyncro.addItem("Asincronico");
	        setStyleName(ChameleonTheme.WINDOW_OPAQUE);
	        // Some basic content for the window
	        FormLayout content = new FormLayout();
	        content.addComponent(id);
	        content.addComponent(ip);
	        content.addComponent(selectSyncro);
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
					if (((String)selectSyncro.getValue()).toUpperCase().equalsIgnoreCase("SINCRONICO")){
						synchronic = true;
					}else{
						synchronic = false;
					}
					addNewIP(configuredID, configuredIP, synchronic);
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
	public MonitorepIpConfigurator() {

	}
	public void init(String group, HashMap<Integer, ConnectionView> actualSet) {
		addStyleName("reindeer");
		mappedIPs = new HashMap<String, ConnectionView>();
		buildMainLayout();
		setCompositionRoot(mainLayout);
		configureGroupBox(actualSet);
		configureLabel(group);
		
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

			/**
			 * 
			 */
			private static final long serialVersionUID = -1691288338526117398L;

			@Override
			public void buttonClick(ClickEvent event) {
		         UI.getCurrent().addWindow(newIPConfiguratorWindow);
		         
			}
		});
		button_1.setCaption("+");
		button_1.setImmediate(false);
		button_1.setDescription("Add new ip");
		button_1.setWidth("-1px");
		button_1.setHeight("-1px");
		mainLayout.addComponent(button_1, "top:154.0px;left:170.0px;");
		
		button_2 = new Button("Aceptar",new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1691288338526117398L;

			@Override
			public void buttonClick(ClickEvent event) {

				ArrayList<ConnectionView> activas = new ArrayList<ConnectionView>();	
				String nuevo = optionGroup_2.getValue().toString().substring(1, 
						optionGroup_2.getValue().toString().length()-1);
				
		        String[] list = nuevo.split(",");
		        for (int i = 0; i < list.length; i++) {
					String string = list[i];					
					activas.add(mappedIPs.get(string.trim()));					
					System.out.println(string);
				}
		        if (mappedIPs.size()==0) {
		        	Notification.show("No IP's Selected, select at least one", Type.WARNING_MESSAGE);
		        	return;
		        }
					facade.saveMonitoreosConnection(activas);

		         
			}
		});
		button_2.setCaption("Aceptar");
		button_2.setImmediate(false);
		button_2.setDescription("Add new ip");
		button_2.setWidth("-1px");
		button_2.setHeight("-1px");
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
	private void configureGroupBox(HashMap<Integer, ConnectionView> actualSet){
		optionGroup_2.setMultiSelect(true);
		String groupValue =  new String();
		Item added;
		for (Integer key:actualSet.keySet()){
			if (actualSet.get(key).isSyncronic())
				groupValue = key+"-"+actualSet.get(key).getIp()+"-"+"Sincronico";
			else 
				groupValue = key+"-"+actualSet.get(key).getIp()+"-"+"Asincronico";
			added = optionGroup_2.addItem(groupValue);
			
			mappedIPs.put(groupValue,actualSet.get(key));
		}
		optionGroup_2.setValue(optionGroup_2.getValue());		
	}
	private void configureLabel(String label){
		// label_1
		label_1 = new Label(label);
		label_1.setImmediate(false);
		label_1.setWidth("140px");
		label_1.setHeight("-1px");
		mainLayout.addComponent(label_1, "top:10.0px;left:20.0px;");		
	}
	 protected void addNewIP(String newID, String newIP, boolean synchronic){
		 Item added;
		 boolean sync = false;
		 String groupValue;
		 if (synchronic)  {
			 groupValue =  newID+"-"+newIP+"-"+"Sincronico";
			 sync = true;
		 }
		 else 
			 groupValue =  newID+"-"+newIP+"-"+"Asincronico";
		 
		 optionGroup_2.addItem(groupValue);
		 added=optionGroup_2.addItem(groupValue);
		 optionGroup_2.select(added);
		 System.out.println("ID "+Integer.parseInt(newID)+" IP"+newIP );
		 mappedIPs.put(groupValue,new ConnectionView(Integer.parseInt(newID),false,newIP,sync));		 
		 optionGroup_2.select(added);
	 
	 }

	 

}
