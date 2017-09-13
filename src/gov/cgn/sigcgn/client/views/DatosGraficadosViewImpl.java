package gov.cgn.sigcgn.client.views;

import gov.cgn.sigcgn.client.util.FormatValue;
import gov.cgn.sigcgn.shared.util.ResultadoContableNivel1;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.ColumnChart;
import com.googlecode.gwt.charts.client.corechart.ColumnChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;

public class DatosGraficadosViewImpl extends Composite {

	private static DatosGraficadosViewImplUiBinder uiBinder = GWT
			.create(DatosGraficadosViewImplUiBinder.class);

	interface DatosGraficadosViewImplUiBinder extends
			UiBinder<Widget, DatosGraficadosViewImpl> {
	}

	@UiField
	HTMLPanel panelChart;

	ColumnChart chart;
	ChartLoader chartLoader;

	public DatosGraficadosViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		chartLoader = new ChartLoader(ChartPackage.CORECHART);
	}
	
	public void initialize(final List<ResultadoContableNivel1> result) {
		chartLoader.loadApi(new Runnable() {
			@Override
			public void run() {
				// Create and attach the chart
				chart = new ColumnChart();
				draw(result);
				panelChart.clear();
				panelChart.add(chart);
			}
		});
	}

	private void draw(List<ResultadoContableNivel1> result) {
		DateTimeFormat fmtDate=DateTimeFormat.getFormat("yyyy");
		Date date = new Date();
		int anio = Integer.parseInt(fmtDate.format(date));
		int[] years = new int[] { anio-3, anio-2, anio-1};

		// Prepare the data
		DataTable dataTable = DataTable.create();
		
		dataTable.addColumn(ColumnType.STRING, "Cuentas");
		for (int i = 0; i < result.size(); i++) {
			dataTable.addColumn( ColumnType.NUMBER, result.get(i).getNombreConcepto());
			result.get(i).setCodigoConcepto((i+1)+"");
		}
		dataTable.addRows(years.length);
		for (int i = 0; i < years.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(years[i]));
		}
		for (ResultadoContableNivel1 r : result) {
			dataTable.setValue(0, Integer.parseInt(r.getCodigoConcepto()), r.getValorAnio3());
			dataTable.setValue(1, Integer.parseInt(r.getCodigoConcepto()), r.getValorAnio2());
			dataTable.setValue(2, Integer.parseInt(r.getCodigoConcepto()), r.getValorAnio1());
			dataTable.setFormattedValue(0, Integer.parseInt(r.getCodigoConcepto()), FormatValue.obtenerFormatoPesos(r.getValorAnio3()));
			dataTable.setFormattedValue(1, Integer.parseInt(r.getCodigoConcepto()), FormatValue.obtenerFormatoPesos(r.getValorAnio2()));
			dataTable.setFormattedValue(2, Integer.parseInt(r.getCodigoConcepto()), FormatValue.obtenerFormatoPesos(r.getValorAnio1()));
		}

		// Draw the chart
		chart.draw(dataTable, getOptionChart());
	}
	
	private ColumnChartOptions getOptionChart(){
		// Set options
		ColumnChartOptions options = ColumnChartOptions.create();
		options.setTitle("Saldo final reportados en los últimos 3 años");
		HAxis hAxis = HAxis.create("Año");
		options.setHAxis(hAxis);
		VAxis vAxis = VAxis.create("Miles de Pesos");
		vAxis.setFormat("$#,##0");
		options.setVAxis(vAxis);
		options.setHeight(300);		
		options.setFontSize(8);
				
		return options;
	}

}
