package gov.cgn.sigcgn.client.rpc;

import gov.cgn.sigcgn.shared.util.InformacionEntidadSIG;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("consultaInfoEntidad.rpc")
public interface ConsultaInformacionEntidadRpc extends RemoteService {
	
	List<InformacionEntidadSIG> consultarInformacionEntidad(String idEntidad);
	
	List<InformacionEntidadSIG> consultarListadoEntidades(
			List<String> listRuleCodeDepto);
	
}
