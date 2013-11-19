package ar.com.edu.uade.view.articulo;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ar.com.edu.uade.ejbfacade.EJBFacade;
import ar.com.edu.uade.utils.InstallArticuloValidatorBlurListener;
import ar.com.edu.uade.utils.ValidatorUtils;
import view.ElectrodomesticoView;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;


public class ElectrodomesticoFormView extends CustomComponent {
	

	private class ButtonSave implements Button.ClickListener{
		
		EJBFacade facade;
		public ButtonSave(){
			super();
			try {
				facade = EJBFacade.getIntance();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void buttonClick(ClickEvent event) {
	        try {
	        	if (editable) {
	        		ValidatorUtils.installSingleValidator(stock,"stock");
	        	}
	        	ValidatorUtils.installSingleValidator(codigo,"textCodigo");
	        	ValidatorUtils.installSingleValidator(marca,"marca");
	        	ValidatorUtils.installSingleValidator(nombre,"nombre");
	        	ValidatorUtils.installSingleValidator(precio,"textPrecio");
	        	//bindeable.setPrecio(Float.parseFloat(precio.getValue()));
	        	ValidatorUtils.installSingleValidator(foto,"foto");
	        	ValidatorUtils.installSingleValidator(fichaTecnica,"fichaTecnica");
	        	ValidatorUtils.installSingleValidator(origen,"origen");
	        	binder.commit();
	            //EJBFacade.getIntance().altaElectrodomestico(bindeable);
	        		facade.altaElectrodomestico(bindeable);
	        } catch (CommitException e) {
	        	try{
	        		for(Field<?> f:binder.getFields()){
	        			System.out.println(f.getCaption());
	        			System.out.println(f.getValue());
	        			f.validate();
	        		}
	        	
	        	}catch(Exception j){
	        		logger.error(j);
	        		j.printStackTrace();
	        	}
	        }	
//	        catch (NamingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
	} 
	
	private static final long serialVersionUID = 1739709695326530748L;
	protected final BeanFieldGroup<ElectrodomesticoView> binder;
	protected FormLayout layout;
	private ElectrodomesticoView bindeable;
	private boolean editable;
	private static final Logger logger = 
			   Logger.getLogger(ElectrodomesticoFormView.class);
	
	
	final AbstractTextField  descripcion ;
	final AbstractTextField  marca;
	final AbstractTextField  nombre;
	final AbstractTextField  precio;
	final AbstractTextField  foto;
	final AbstractTextField  codigo;
	final AbstractTextField  fichaTecnica;
	final AbstractTextField  stock ;
	final AbstractTextField  origen;

	
    public ElectrodomesticoFormView() {
    		super();
			binder = new BeanFieldGroup<ElectrodomesticoView>(ElectrodomesticoView.class);
			descripcion = (AbstractTextField) binder.buildAndBind("Descripcion", "descripcion");
			descripcion.setNullRepresentation("");
			marca=(AbstractTextField) binder.buildAndBind("Marca", "marca");
			marca.setNullRepresentation("");
			nombre=(AbstractTextField) binder.buildAndBind("Nombre", "nombre");
			nombre.setNullRepresentation("");
			precio=(AbstractTextField) binder.buildAndBind("Precio", "textPrecio");
			precio.setNullRepresentation("");
			foto=(AbstractTextField) binder.buildAndBind("Foto", "foto");
			foto.setNullRepresentation("");
			codigo=(AbstractTextField) binder.buildAndBind("Codigo", "textCodigo");
			codigo.setNullRepresentation("");
			fichaTecnica=(AbstractTextField) binder.buildAndBind("Ficha Tecnica", "fichaTecnica");
			fichaTecnica.setNullRepresentation("");
			stock =(AbstractTextField) binder.buildAndBind("Stock", "stock");
			stock.setNullRepresentation("");
			
			origen =(AbstractTextField) binder.buildAndBind("Origen", "origen");
			origen.setNullRepresentation("");
	}
    public void init(){
        layout = new FormLayout();
        setCompositionRoot(layout);
        
        bindeable = new ElectrodomesticoView();
        binder.setItemDataSource(bindeable);
        editable = false;
        buildLayout(layout, binder);
    }
    public void  init(ElectrodomesticoView bean){
        layout = new FormLayout();
        layout.setStyleName("chameleon");
        setCompositionRoot(layout);
       
        bindeable = bean;
        binder.setItemDataSource(bindeable);
        editable = true;
        buildLayout(layout, binder);
    }
    private void buildLayout(FormLayout layout,
			final BeanFieldGroup<ElectrodomesticoView> binder) {
    	
//    	final AbstractTextField  descripcion = (AbstractTextField) binder.buildAndBind("Descripcion", "descripcion");
//    	descripcion.setNullRepresentation("");
//    	final AbstractTextField  marca=(AbstractTextField) binder.buildAndBind("Marca", "marca");
//    	marca.setNullRepresentation("");
//    	final AbstractTextField  nombre=(AbstractTextField) binder.buildAndBind("Nombre", "nombre");
//    	nombre.setNullRepresentation("");
//    	final AbstractTextField  precio=(AbstractTextField) binder.buildAndBind("Precio", "textPrecio");
//    	precio.setNullRepresentation("");
//    	final AbstractTextField  foto=(AbstractTextField) binder.buildAndBind("Foto", "foto");
//    	foto.setNullRepresentation("");
//    	final AbstractTextField  codigo=(AbstractTextField) binder.buildAndBind("Codigo", "textCodigo");
//    	//final LongField  codigo=(LongField) binder.buildAndBind("Codigo", "codigo");
//    	codigo.setNullRepresentation("");
//    	final AbstractTextField  fichaTecnica=(AbstractTextField) binder.buildAndBind("Ficha Tecnica", "fichaTecnica");
//    	fichaTecnica.setNullRepresentation("");
//    	final AbstractTextField  stock =(AbstractTextField) binder.buildAndBind("Stock", "stock");
//    	stock.setNullRepresentation("");
//
//    	final AbstractTextField  origen =(AbstractTextField) binder.buildAndBind("Origen", "origen");
//    	origen.setNullRepresentation("");
    	
    	descripcion.addBlurListener(new InstallArticuloValidatorBlurListener(descripcion, "descripcion"));
    	marca.addBlurListener(new InstallArticuloValidatorBlurListener(marca,"marca"));
    	nombre.addBlurListener(new InstallArticuloValidatorBlurListener(nombre,"nombre"));
    	precio.addBlurListener(new InstallArticuloValidatorBlurListener(precio,"textPrecio"));
    	foto.addBlurListener(new InstallArticuloValidatorBlurListener(foto,"tipo"));
    	fichaTecnica.addBlurListener(new InstallArticuloValidatorBlurListener(fichaTecnica,"fichaTecnica"));
    	origen.addBlurListener(new InstallArticuloValidatorBlurListener(origen,"origen"));
    	codigo.addBlurListener(new InstallArticuloValidatorBlurListener(codigo,"textCodigo"));
    	layout.addComponent(codigo);
    	layout.addComponent(descripcion );
    	layout.addComponent(marca);
    	layout.addComponent(nombre);
    	layout.addComponent(precio);
    	layout.addComponent(foto);
    	layout.addComponent(fichaTecnica);
    	layout.addComponent(origen);
    	if (editable){	    		
    		layout.addComponent(stock);
    		stock.addBlurListener(new InstallArticuloValidatorBlurListener(stock,"stock"));
    	}
    	// Buffer the form content
    	binder.setBuffered(true);
    	Button okButton =  new Button("OK");
    	okButton.addClickListener(new ButtonSave());
    	layout.addComponent(okButton);
    }

   
}