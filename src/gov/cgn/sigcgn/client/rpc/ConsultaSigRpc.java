package gov.cgn.sigcgn.client.rpc;

import gov.cgn.sigcgn.shared.util.ResultadoContableNivel1;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("consultaSig.rpc")
public interface ConsultaSigRpc extends RemoteService{
	
	List<ResultadoContableNivel1> consultaCuentasNivel1(int idEntidad);

}
