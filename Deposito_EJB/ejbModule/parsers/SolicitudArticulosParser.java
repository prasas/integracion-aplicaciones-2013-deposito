package parsers;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import dto.SolicitudArticuloItemDTO;
import dto.SolicitudArticulosDTO;
import entities.SolicitudArticulos;
import entities.SolicitudArticulosItem;

public class SolicitudArticulosParser implements Parser<SolicitudArticulosDTO> {

	@Override
	public SolicitudArticulosDTO toObject(String data) throws ParserException {
		JAXBContext jaxbCtx;
		SolicitudArticulosDTO sa = null;

		try {
			jaxbCtx = JAXBContext.newInstance(SolicitudArticulosDTO.class);
			Unmarshaller u = jaxbCtx.createUnmarshaller();

			u.setEventHandler(new ValidationEventHandler() {
				public boolean handleEvent(ValidationEvent event) {
					throw new RuntimeException(event.getMessage(), event.getLinkedException());
				}

			});
			StringReader reader = new StringReader(data);
			sa = (SolicitudArticulosDTO) u.unmarshal(reader);
		} catch (Exception e) {
			// TODO AR - Log error
			throw(new ParserException("Error parseando solicitud de Articulos \n" + data, e ));			
		}

		return sa;
	}

	@Override
	public String toString(SolicitudArticulosDTO data) throws ParserException {
		// TODO  hace falta esto? 
		return null;
	}
	public String toXML(SolicitudArticulos a){
		// TODO hace falta esto? 
		return null;
	}

	public String toJson(SolicitudArticulosDTO sa){
		String json="{\n"
				+ "   idModulo:6,\n"
				+ "   idSolicitud:"+ sa.getIdSolicitud();
		if (sa.getLista().size()>0){
			int i=1;
			int j=sa.getLista().size();
			json=json + ",\n\n   items: [\n";
			for (SolicitudArticuloItemDTO a : sa.getLista()){
				if (i<j) {
					json=json + "   {codigo:"+a.getCodigo()+", cantidad: "+a.getCantidad()+"},\n";
					i=i+1;
				} else{
					json=json + "   {codigo:"+a.getCodigo()+", cantidad: "+a.getCantidad()+"}\n";					
				}
			}
			json=json + "   ]\n}\n";
		} else { 
			json=json + "\n}\n";
		}
		
		return json;		
		
	}
	public String toJson(SolicitudArticulos sa){
		String json="{\n"
				+ "   idModulo:6,\n"
				+ "   idSolicitud:"+ sa.getSolicitudId();
		if (sa.getItems().size()>0){
			int i=1;
			int j=sa.getItems().size();
			json=json + ",\n\n   items: [\n";
			for (SolicitudArticulosItem a : sa.getItems()){
				if (i<j) {
					json=json + "   {codigo:"+a.getArticulo().getCodigo()+", cantidad: "+a.getCantidad()+"},\n";
					i=i+1;
				} else{
					json=json + "   {codigo:"+a.getArticulo().getCodigo()+", cantidad: "+a.getCantidad()+"}\n";					
				}
			}
			json=json + "   ]\n}\n";
		} else { 
			json=json + "\n}\n";
		}
		
		return json;		
		
	}
	
}
