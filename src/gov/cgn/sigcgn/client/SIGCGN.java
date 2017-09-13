package gov.cgn.sigcgn.client;

import gov.cgn.sigcgn.client.lib.AlertAdder;
import gov.cgn.sigcgn.client.lib.AlertCallback;
import gov.cgn.sigcgn.client.lib.AlertCallback.SuccessCommand;
import gov.cgn.sigcgn.client.rpc.ConsultaSigRpc;
import gov.cgn.sigcgn.client.rpc.ConsultaSigRpcAsync;
import gov.cgn.sigcgn.client.rpc.ConsultaInformacionEntidadRpc;
import gov.cgn.sigcgn.client.rpc.ConsultaInformacionEntidadRpcAsync;
import gov.cgn.sigcgn.client.views.DatosEntidadInfoWindowsViewImpl;
import gov.cgn.sigcgn.client.views.DatosGraficadosViewImpl;
import gov.cgn.sigcgn.client.views.DatosTabuladosViewImpl;
import gov.cgn.sigcgn.shared.util.BuscarEntidadOracle;
import gov.cgn.sigcgn.shared.util.BuscarEntidadSuggestion;
import gov.cgn.sigcgn.shared.util.InformacionEntidadSIG;
import gov.cgn.sigcgn.shared.util.ResultadoContableNivel1;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SIGCGN implements EntryPoint {

	DatosTabuladosViewImpl tabla;
	DatosGraficadosViewImpl grafica;
	DatosEntidadInfoWindowsViewImpl infoWIndows;
	List<String> filtroEntidades;
	VerticalPanel panelAutoCompletar;
	String codigoEntidad;
	String consulta;
	SuggestBox suggestionBox;

	private List<String> listRuleCodeDepto;
	private List<String> listRuleCodeMnpio;
	private List<String> listRuleCodeEntid; 

	private final ConsultaSigRpcAsync sigServicio = GWT.create(ConsultaSigRpc.class);
	private final ConsultaInformacionEntidadRpcAsync entidadServicio = GWT.create(ConsultaInformacionEntidadRpc.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		tabla = new DatosTabuladosViewImpl();
		grafica = new DatosGraficadosViewImpl();
		infoWIndows = new DatosEntidadInfoWindowsViewImpl();
		loadListaEntidades("Departamentos.json");
		exporCargarCapaJS();	
		exportRefrescarDatosJS();
		exportinicializarInfoWindows();
		exportCodigoEntidadJS();
		exportSuggestText();
		RootPanel.get("gwtContainer").add(panelAutoCompletar);
	}


	private void loadListaEntidades(String tipo) {
		final AcceptsOneWidget alertContainer = new SimplePanel();
		
		listRuleCodeDepto = new ArrayList<>();
		listRuleCodeMnpio = new ArrayList<>();
		listRuleCodeEntid = new ArrayList<>();
		
		listRuleCodeEntid = null;
		
		listRuleCodeDepto.add("28121");
		
		listRuleCodeMnpio.add("28116");
		listRuleCodeMnpio.add("28117");
		listRuleCodeMnpio.add("28119");
		listRuleCodeMnpio.add("28120");
		listRuleCodeMnpio.add("28148");
		
		SuccessCommand<List<InformacionEntidadSIG>> command = new SuccessCommand<List<InformacionEntidadSIG>>() {

			@Override
			public void onSuccess(List<InformacionEntidadSIG> filtroEntidades,
					AlertAdder adder) {
				
				BuscarEntidadOracle oracleBuscarEntidad = new BuscarEntidadOracle(
						filtroEntidades);

				suggestionBox = new SuggestBox(oracleBuscarEntidad);
				suggestionBox.setStyleName("gwt-SuggestBox");

				panelAutoCompletar = new VerticalPanel();
				panelAutoCompletar.add(suggestionBox);

				suggestionBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

							@Override
							public void onSelection(
									SelectionEvent<Suggestion> event) {
								
								BuscarEntidadSuggestion value = (BuscarEntidadSuggestion) event
										.getSelectedItem();
								codigoEntidad = value.getCodigoEntidad();
							}
						});
				
				RootPanel.get("gwtContainer").clear();
				RootPanel.get("gwtContainer").add(panelAutoCompletar);
				suggestionBox.setFocus(true);
			}
		};
		
		if(tipo.equals("Departamentos.json")){
			entidadServicio.consultarListadoEntidades(listRuleCodeDepto,
					new AlertCallback<List<InformacionEntidadSIG>>(alertContainer,
							command));
		}
		if(tipo.equals("CoorR.json")){
			entidadServicio.consultarListadoEntidades(listRuleCodeEntid,
					new AlertCallback<List<InformacionEntidadSIG>>(alertContainer,
							command));
		}		
		else{
			if(tipo.equals("Municipios.json")){
			entidadServicio.consultarListadoEntidades(listRuleCodeMnpio,
					new AlertCallback<List<InformacionEntidadSIG>>(alertContainer,
							command));
			}
		}		
	}
	
//	INICIALIZAR INFOWINDOWS

	private void inicializarInfoWindows(String idEntidad) {
		final AcceptsOneWidget alertContainer = new SimplePanel();
		SuccessCommand<List<InformacionEntidadSIG>> command = new SuccessCommand<List<InformacionEntidadSIG>>() {

			@Override
			public void onSuccess(List<InformacionEntidadSIG> result,
					AlertAdder adder) {
				pintarDatosInfoWindows(result, alertContainer);
}
		};
		entidadServicio.consultarInformacionEntidad(idEntidad,
				new AlertCallback<List<InformacionEntidadSIG>>(alertContainer,
						command));
	}
	
//	..........................................................
	public void SuggestText(String idEntidad, String nombreEntidad){
		nombreEntidad=nombreEntidad.toUpperCase();
		String text = idEntidad + " - " + nombreEntidad ;
		suggestionBox.setText(text);

	}
//............................................................
//	refrescarDatos
	public void refrescarDatos(final int idEntidad) {
		
		final AcceptsOneWidget alertContainer = new SimplePanel();
		SuccessCommand<List<ResultadoContableNivel1>> command = new SuccessCommand<List<ResultadoContableNivel1>>() {
			Boolean pintarDatos	= true;
			
			@Override
			public void onSuccess(List<ResultadoContableNivel1> result,
					AlertAdder adder) {
				
				if (idEntidad == 000) {						
					pintarDatos=false;
					RootPanel.get("chartDiv").clear();
					RootPanel.get("datagrid").clear();
					RootPanel.get("info").clear();					
				}		
				else{
					pintarDatos=true;
				}		
				
				pintarDatosTabulados(result, alertContainer,pintarDatos);
				pintarDatosGraficados(result, alertContainer);
			}
		};
		sigServicio.consultaCuentasNivel1(idEntidad,
				new AlertCallback<List<ResultadoContableNivel1>>(
						alertContainer, command));
	}
	
	//pintarDatosGraficados.................................................................................................................
	private void pintarDatosGraficados(List<ResultadoContableNivel1> result,
			AcceptsOneWidget alertContainer) {
		grafica.initialize(result);
		
		
		RootPanel.get("chartDiv").clear();
		RootPanel.get("chartDiv").add(grafica);
	}
	
	//pintarDatosTabulados
	private void pintarDatosTabulados(List<ResultadoContableNivel1> result,
			AcceptsOneWidget alertContainer, Boolean pintarDatos) {

		final ListDataProvider<ResultadoContableNivel1> dataProvider = new ListDataProvider<ResultadoContableNivel1>();

		tabla.setAlertContainer((SimplePanel) alertContainer);
		
		if(pintarDatos == true){
			dataProvider.setList(result);
		}else{
			dataProvider.setList(null);
		}
		
		dataProvider.addDataDisplay(tabla.getTable());

		RootPanel.get("datagrid").clear();
		RootPanel.get("datagrid").add(tabla);
	}
	
	//pintarDatosInfoWindows
	private void pintarDatosInfoWindows(List<InformacionEntidadSIG> result,	AcceptsOneWidget alertContainer) {

		final ListDataProvider<InformacionEntidadSIG> dataProvider = new ListDataProvider<InformacionEntidadSIG>();

		tabla.setAlertContainer((SimplePanel) alertContainer);
		dataProvider.setList(result);
		dataProvider.addDataDisplay(infoWIndows.getTable());

		RootPanel.get("info").clear();
		RootPanel.get("info").add(infoWIndows);
	}

	public void inicializarInfoWindowsJS(String idEntidad) {
		inicializarInfoWindows(idEntidad);
	}
	
	public void cargarListaEntidadesJS(String tipo) {
		loadListaEntidades(tipo);
	}
	
	public void SuggestTextJS(String idEntidad, String nombreEntidad) {
		SuggestText(idEntidad , nombreEntidad);
	}
	
	public void refrescarDatosJS(int idEntidad) {
		refrescarDatos(idEntidad);
	}

	public native void exportRefrescarDatosJS() /*-{
		var that = this;
		$wnd.refrescarDatosJS = $entry(function(idEntidad) {
			that.@gov.cgn.sigcgn.client.SIGCGN::refrescarDatosJS(I)(idEntidad);
		});
	}-*/;

	public native void exportinicializarInfoWindows() /*-{
		var that = this;
		$wnd.inicializarInfoWindows = $entry(function(idEntidad) {
			that.@gov.cgn.sigcgn.client.SIGCGN::inicializarInfoWindowsJS(Ljava/lang/String;)(idEntidad);
		});
	}-*/;
	
	public native void exportSuggestText() /*-{
	var that = this;
	$wnd.SuggestText = $entry(function(idEntidad,nombreEntidad) {
		that.@gov.cgn.sigcgn.client.SIGCGN::SuggestTextJS(Ljava/lang/String;Ljava/lang/String;)(idEntidad,nombreEntidad);
	});
}-*/;

	public native void exportCodigoEntidadJS() /*-{
		var that = this;
			
		$wnd.exportarCodigo = $entry(function() {
			$wnd.centerMap(that.@gov.cgn.sigcgn.client.SIGCGN::codigoEntidad);
			that.@gov.cgn.sigcgn.client.SIGCGN::codigoEntidad = null;
		});

	}-*/;

	public native void exporCargarCapaJS() /*-{
		var that = this;

		$wnd.loadListaEntidades = $entry(function(tipo) {
			that.@gov.cgn.sigcgn.client.SIGCGN::loadListaEntidades(Ljava/lang/String;)(tipo);
		});
	}-*/;
}



