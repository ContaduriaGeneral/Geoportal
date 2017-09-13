package gov.cgn.sigcgn.shared.util;

import java.io.Serializable;

public class ResultadoContableNivel1  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoConcepto;
	private String nombreConcepto ;
	private String valorAnio1;
	private String valorAnio2;
	private String valorAnio3;
	
	public String getCodigoConcepto() {
		return codigoConcepto;
	}
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	public String getNombreConcepto() {
		return nombreConcepto;
	}
	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}
	public String getValorAnio1() {
		return valorAnio1;
	}
	public void setValorAnio1(String valorAnio1) {
		this.valorAnio1 = valorAnio1;
	}
	public String getValorAnio2() {
		return valorAnio2;
	}
	public void setValorAnio2(String valorAnio2) {
		this.valorAnio2 = valorAnio2;
	}
	public String getValorAnio3() {
		return valorAnio3;
	}
	public void setValorAnio3(String valorAnio3) {
		this.valorAnio3 = valorAnio3;
	}
}
