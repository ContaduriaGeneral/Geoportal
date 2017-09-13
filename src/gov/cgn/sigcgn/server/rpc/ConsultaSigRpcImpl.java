package gov.cgn.sigcgn.server.rpc;

import gov.cgn.modelo.reportes.ValorConceptoAnualDTO;
import gov.cgn.sigcgn.client.rpc.ConsultaSigRpc;
import gov.cgn.sigcgn.server.core.GWTController;
import gov.cgn.sigcgn.shared.util.MapeadorObjetos;
import gov.cgn.sigcgn.shared.util.ResultadoContableNivel1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/**/consultaSig.rpc")
@SuppressWarnings("serial")
public class ConsultaSigRpcImpl extends GWTController implements ConsultaSigRpc {

	@Override
	public List<ResultadoContableNivel1> consultaCuentasNivel1(int idEntidad) {
		return null;
	}
	
	

	
	
}
