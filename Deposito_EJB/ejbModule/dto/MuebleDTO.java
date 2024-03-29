package dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "articulo")
public class MuebleDTO extends ArticuloDTO{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347083384610652409L;
	@NotNull
	private String material;
	
	public MuebleDTO(){
		super();
		material = null;
		setTipo("mueble");
	}

	@XmlElement
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
}
