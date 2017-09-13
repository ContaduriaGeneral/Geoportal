package gov.cgn.sigcgn.client.util;

import com.google.gwt.i18n.client.NumberFormat;

public class FormatValue {
	
	
	static public String obtenerFormatoPesos(String valor){
		final NumberFormat formato = NumberFormat.getFormat("$ #,##0");
		if(valor==null || valor.equals("")){
			return "";
		}
		return formato.format(Double.parseDouble(valor));
	} 
	
	

}
