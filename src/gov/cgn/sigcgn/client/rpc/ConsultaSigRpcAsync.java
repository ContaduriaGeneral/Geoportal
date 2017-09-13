package gov.cgn.sigcgn.client.rpc;

import gov.cgn.sigcgn.shared.util.ResultadoContableNivel1;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConsultaSigRpcAsync {

		void consultaCuentasNivel1(int idEntidad,AsyncCallback<List<ResultadoContableNivel1>> callback);

}
