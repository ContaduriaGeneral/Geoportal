package gov.cgn.sigcgn.shared.util;

import java.util.ArrayList;
import java.util.List;

import gov.cgn.modelo.entidades.InformacionEntidadDTO;
import gov.cgn.modelo.reportes.ValorConceptoAnualDTO;


public class MapeadorObjetos {
	
	public static List<ResultadoContableNivel1> getResultadoMapeado(List<ValorConceptoAnualDTO> dtoValores){
		
		List<ResultadoContableNivel1> resultadoList= new ArrayList<ResultadoContableNivel1>();
		String codTmp = "";
		ResultadoContableNivel1 resultado = null;

		for (ValorConceptoAnualDTO valorDTO : dtoValores) {
			
			if(!codTmp.equals(valorDTO.getCodConcepto())){
				if(!codTmp.equals("")){
					resultadoList.add(resultado);
				}
				codTmp = valorDTO.getCodConcepto();
				resultado = new ResultadoContableNivel1();
				competarInfo(resultado, valorDTO);
			}else{
				competarInfo(resultado, valorDTO);
			}			
		}
		//Se adiciona el último item
		resultadoList.add(resultado);
		return resultadoList;
	}

	private static void competarInfo(ResultadoContableNivel1 resultado,
			ValorConceptoAnualDTO valorDTO) {
		resultado.setCodigoConcepto(valorDTO.getCodConcepto());
		resultado.setNombreConcepto(valorDTO.getNombreConcepto());
		if(resultado.getValorAnio1() == null){
			resultado.setValorAnio1(valorDTO.getValor());
		}else if(resultado.getValorAnio2() == null){
			resultado.setValorAnio2(valorDTO.getValor());
		}else if(resultado.getValorAnio3() == null){
			resultado.setValorAnio3(valorDTO.getValor());
		}
	}
	
	public static List<InformacionEntidadSIG> getInfoEntidadMapeada(List<InformacionEntidadDTO> ListEntidades){
			
		List<InformacionEntidadSIG> resultadoList= new ArrayList<InformacionEntidadSIG>();
		InformacionEntidadSIG resultado = null;

		for (InformacionEntidadDTO infoEntidad : ListEntidades) {
				
			resultado = new InformacionEntidadSIG();
			
			resultado.setId_entidad(infoEntidad.getId_entidad());
			resultado.setRazon_social(infoEntidad.getRazon_social());
			resultado.setNit(infoEntidad.getNit());
			resultado.setDireccion(infoEntidad.getDireccion());
			resultado.setEmailRepLegal(infoEntidad.getEmail());
			resultado.setRepresentanteLegal(infoEntidad.getRepresentanteLegal());
			
			
			resultadoList.add(resultado);
		}
		
		return resultadoList;
		
		
	}

	public static List<InformacionEntidadSIG> getListaEntidadesAutocompletar(
		List<InformacionEntidadDTO> listadoEntidades) {
		
		List<InformacionEntidadSIG> list = new ArrayList<InformacionEntidadSIG>();
		InformacionEntidadSIG entidad = null;
		
		for (InformacionEntidadDTO infoEntidad : listadoEntidades) {
			
			entidad = new InformacionEntidadSIG();			
			entidad.setId_entidad(infoEntidad.getId_entidad());
			entidad.setRazon_social(infoEntidad.getRazon_social());
			
			list.add(entidad);
			
		}
		
		
		return list;
	}

}
