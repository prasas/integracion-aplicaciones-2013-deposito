package view;

import javax.validation.constraints.NotNull;




public class InfantilView extends ArticuloView{


	private static final long serialVersionUID = 1L;
	@NotNull
	private String edadRecomendada;
	
	public InfantilView(){
		super();
		edadRecomendada = null;
		setTipo("infantil");
	}


	public String getEdadRecomendada() {
		return edadRecomendada;
	}

	public void setEdadRecomendada(String edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}


	@Override
	public String getFichaTecnica() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getTalle() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}
}
