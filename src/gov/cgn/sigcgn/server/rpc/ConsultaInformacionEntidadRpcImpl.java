package gov.cgn.sigcgn.server.rpc;

import gov.cgn.sigcgn.client.rpc.ConsultaInformacionEntidadRpc;
import gov.cgn.sigcgn.server.core.GWTController;
import gov.cgn.sigcgn.shared.util.InformacionEntidadSIG;
import gov.cgn.sigcgn.shared.util.MapeadorObjetos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/**/consultaInfoEntidad.rpc")
@SuppressWarnings("serial")


public class ConsultaInformacionEntidadRpcImpl extends GWTController implements
		ConsultaInformacionEntidadRpc {

	@Override
	public List<InformacionEntidadSIG> consultarInformacionEntidad(
			String idEntidad) {

		return null;
	}

	@Override
	public List<InformacionEntidadSIG> consultarListadoEntidades(List<String> ruleCode) {

		return null;
	}

}
