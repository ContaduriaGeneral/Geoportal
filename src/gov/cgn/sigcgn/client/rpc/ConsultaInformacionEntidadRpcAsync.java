package gov.cgn.sigcgn.client.rpc;

import gov.cgn.sigcgn.shared.util.InformacionEntidadSIG;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConsultaInformacionEntidadRpcAsync {

	void consultarInformacionEntidad(String string,
			AsyncCallback<List<InformacionEntidadSIG>> callback);

	void consultarListadoEntidades(List<String> listRuleCodeDepto,
			AsyncCallback<List<InformacionEntidadSIG>> callback);
	
}
