package gov.cgn.sigcgn.client.views;

import gov.cgn.sigcgn.client.api.view.DatosTabuladosView;
import gov.cgn.sigcgn.client.lib.cellview.CellTableExt;
import gov.cgn.sigcgn.client.lib.cellview.TextColumnExt;
import gov.cgn.sigcgn.client.util.FormatValue;
import gov.cgn.sigcgn.shared.util.ResultadoContableNivel1;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DatosTabuladosViewImpl extends Composite implements DatosTabuladosView {

	private static DatosTabuladosViewImplUiBinder uiBinder = GWT.create(DatosTabuladosViewImplUiBinder.class);

	interface DatosTabuladosViewImplUiBinder extends UiBinder<Widget, DatosTabuladosViewImpl> {
	}

	
	@UiField
	CellTableExt<ResultadoContableNivel1> table;

	@UiField
	SimplePanel alertContainer;


	public DatosTabuladosViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setupTable();
	}

	private void setupTable() {
		
		table.setWidth("100%",true);
		table.setColumnWidth(0, 34.0, Unit.PCT);
		table.setColumnWidth(1, 22.0, Unit.PCT);
		table.setColumnWidth(2, 22.0, Unit.PCT);
		table.setColumnWidth(3, 22.0, Unit.PCT);	

		
		DateTimeFormat fmtDate=DateTimeFormat.getFormat("yyyy");
		Date date = new Date();
		int anio = Integer.parseInt(fmtDate.format(date));
		
		
		
		String Scuentas = "Cuentas / Saldo final anual (Miles de Pesos)";
		
	   
		TextColumnExt<ResultadoContableNivel1> cuentas = new TextColumnExt<ResultadoContableNivel1>(table,Scuentas) {
			@Override
			
			public String getValue(ResultadoContableNivel1 object) {
				return object.getCodigoConcepto() + "." + object.getNombreConcepto();
			}
		};
		cuentas.setCellStyleNames("First-column");
	
		
					
		TextColumnExt<ResultadoContableNivel1> año1 = new TextColumnExt<ResultadoContableNivel1>(table, String.valueOf(anio-3)) {
			@Override
			public String getValue(ResultadoContableNivel1 object) {
				return FormatValue.obtenerFormatoPesos(object.getValorAnio3());
			}
		};
		año1.setCellStyleNames("table-number");
		
		TextColumnExt<ResultadoContableNivel1> año2 = new TextColumnExt<ResultadoContableNivel1>(table, String.valueOf(anio-2)) {
			@Override
			public String getValue(ResultadoContableNivel1 object) {
				return FormatValue.obtenerFormatoPesos(object.getValorAnio2());
			}
		};
		año2.setCellStyleNames("table-number");
		
		TextColumnExt<ResultadoContableNivel1> año3 = new TextColumnExt<ResultadoContableNivel1>(table, String.valueOf(anio-1)) {
			@Override
			public String getValue(ResultadoContableNivel1 object) {
				return FormatValue.obtenerFormatoPesos(object.getValorAnio1());
			}
		};
		año3.setCellStyleNames("table-number");
		
		table.getHeader(0).setHeaderStyleNames("table-header");
		table.getHeader(1).setHeaderStyleNames("table-header");
		table.getHeader(2).setHeaderStyleNames("table-header");
		table.getHeader(3).setHeaderStyleNames("table-header");
		
	}

	
	public CellTableExt<ResultadoContableNivel1> getTable() {
		return table;
	}

	@Override
	public AcceptsOneWidget getAlertContainer() {
		return alertContainer;
	}
	
	
	public void setAlertContainer(SimplePanel alertContainer) {
		this.alertContainer = alertContainer;
	}

}
