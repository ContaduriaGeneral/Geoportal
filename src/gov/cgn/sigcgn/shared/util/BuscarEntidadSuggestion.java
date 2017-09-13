package gov.cgn.sigcgn.shared.util;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class BuscarEntidadSuggestion implements Suggestion {
	
	private String buscarEntidadDisplay;
	
	private String buscarEntidadReplacement;
	
	private String codigoEntidad;
	
	private String nombreEntidad;
	
	public BuscarEntidadSuggestion() {
	}
	
	public BuscarEntidadSuggestion(String codigoEntidad, String nombreEntidad) {
		this.codigoEntidad=codigoEntidad;
		this.nombreEntidad=nombreEntidad;
		
		buscarEntidadDisplay = codigoEntidad +" - " +nombreEntidad;	
		buscarEntidadReplacement = codigoEntidad +" - " +nombreEntidad;
		
	}
	
	public String getCodigoEntidad() {
		return codigoEntidad;
	}

	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	@Override
	public String getDisplayString() {
		
		return this.buscarEntidadDisplay;
	}

	@Override
	public String getReplacementString() {
		
		return this.buscarEntidadReplacement;
	}

}
