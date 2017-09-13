package gov.cgn.sigcgn.client.views;


import gov.cgn.sigcgn.client.api.view.DatosEntidadInfoWindowsView;
import gov.cgn.sigcgn.client.lib.cellview.CellTableExt;
import gov.cgn.sigcgn.client.lib.cellview.TextColumnExt;
import gov.cgn.sigcgn.shared.util.InformacionEntidadSIG;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DatosEntidadInfoWindowsViewImpl extends Composite implements DatosEntidadInfoWindowsView {
	
	private static DatosEntidadInfoWindowsViewImplUiBinder uiBinder = GWT.create(DatosEntidadInfoWindowsViewImplUiBinder.class);

	interface DatosEntidadInfoWindowsViewImplUiBinder extends UiBinder<Widget, DatosEntidadInfoWindowsViewImpl> {
	}

	
	@UiField
	CellTableExt<InformacionEntidadSIG> table;

	@UiField
	SimplePanel alertContainer;

	
	public DatosEntidadInfoWindowsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setupTable();
	}

	
	private void setupTable() {
		
			
		table.setWidth("100%",true);
		table.setColumnWidth(0, 14.0, Unit.PCT);
		table.setColumnWidth(1, 17.0, Unit.PCT);
		table.setColumnWidth(2, 14.0, Unit.PCT);
		table.setColumnWidth(3, 16.0, Unit.PCT);
		table.setColumnWidth(4, 20.0, Unit.PCT);
		table.setColumnWidth(5, 19.0, Unit.PCT);
		
		
		
		TextColumnExt<InformacionEntidadSIG> codigo = new TextColumnExt<InformacionEntidadSIG>(table, "CÓDIGO") {
			@Override
			public String getValue(InformacionEntidadSIG object) {
				return object.getId_entidad();
			}
		};
		codigo.setCellStyleNames("First-column");
		
		
		TextColumnExt<InformacionEntidadSIG> nombre = new TextColumnExt<InformacionEntidadSIG>(table, "NOMBRE") {
			@Override
			public String getValue(InformacionEntidadSIG object) {
				return object.getRazon_social();
			}
		};
		nombre.setCellStyleNames("table-text");
		
		TextColumnExt<InformacionEntidadSIG> nit = new TextColumnExt<InformacionEntidadSIG>(table, "NIT") {
			@Override
			public String getValue(InformacionEntidadSIG object) {
				return object.getNit();
			}
		};
		nit.setCellStyleNames("table-text");
		
		TextColumnExt<InformacionEntidadSIG> dir = new TextColumnExt<InformacionEntidadSIG>(table, "DIRECCIÓN") {
			@Override
			public String getValue(InformacionEntidadSIG object) {
				return object.getDireccion();
			}
		};
		dir.setCellStyleNames("table-text");
		
		TextColumnExt<InformacionEntidadSIG> email = new TextColumnExt<InformacionEntidadSIG>(table, "EMAIL") {
			@Override
			public String getValue(InformacionEntidadSIG object) {
				return object.getEmailRepLegal();
			}
			
		};
		email.setCellStyleNames("table-text");
		
		TextColumnExt<InformacionEntidadSIG> representante = new TextColumnExt<InformacionEntidadSIG>(table, "REPRESENTANTE") {
			@Override
			public String getValue(InformacionEntidadSIG object) {
				return object.getRepresentanteLegal();
			}
		};
		representante.setCellStyleNames("table-text");
		
		
		table.getHeader(0).setHeaderStyleNames("table-header");
		table.getHeader(1).setHeaderStyleNames("table-header");
		table.getHeader(2).setHeaderStyleNames("table-header");
		table.getHeader(3).setHeaderStyleNames("table-header");
		table.getHeader(4).setHeaderStyleNames("table-header");
		table.getHeader(5).setHeaderStyleNames("table-header");
	}
	
	public CellTableExt<InformacionEntidadSIG> getTable() {
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