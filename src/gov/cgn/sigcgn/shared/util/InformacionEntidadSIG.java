package gov.cgn.sigcgn.shared.util;

import java.io.Serializable;


/**
 * Esta Clase es la Abstracion de la consulta de la informacion basica de
 * una entidad presente en el CHIP
 * 
 * @author hlamprea
 *
 */
public class InformacionEntidadSIG implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id_entidad;

	private String nit;

	private String razon_social;

	private String representanteLegal;

	private String direccion;

	private String emailRepLegal;

	public String getId_entidad() {
		return id_entidad;
	}

	public void setId_entidad(String id_entidad) {
		this.id_entidad = id_entidad;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmailRepLegal() {
		return emailRepLegal;
	}

	public void setEmailRepLegal(String email) {
		this.emailRepLegal = email;
	}
	
	
	
}
