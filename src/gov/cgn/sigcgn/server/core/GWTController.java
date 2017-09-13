package gov.cgn.sigcgn.server.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Clase servlet base para todos las implementaciones de controladores GWT-RPC.
 * 
 * @author gusrod
 * 
 */
@SuppressWarnings("serial")
public class GWTController extends RemoteServiceServlet implements ServletContextAware, Controller {
	
	private static Logger log = Logger.getLogger(GWTController.class);

	protected ServletContext servletContext;

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		doPost(req, res);
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public WebApplicationContext getContextSpring(){
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	

}

