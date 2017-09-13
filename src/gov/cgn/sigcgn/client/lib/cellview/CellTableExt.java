package gov.cgn.sigcgn.client.lib.cellview;

import gov.cgn.sigcgn.client.lib.AlertType;
import gov.cgn.sigcgn.client.lib.ui.AlertWidget;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Widget;

/**
 * {@link CellTable} configurado con {@link KeyboardSelectionPolicy.DISABLED},
 * para que no se muestre un recuadro alrededor de las celdas cuando se
 * selecciona una fila.
 * 
 * @author gusrod
 * 
 * @param <T>
 */
public class CellTableExt<T> extends CellTable<T> {

	public CellTableExt() {
		this(new AlertWidget(AlertType.none, null, "No se encontraron registros."));
	}

	public CellTableExt(Widget emptyTableWidget) {
		setDefaultKeyboardSelectionPolicy();
		setEmptyTableWidget(emptyTableWidget);
	}

	public CellTableExt(final int pageSize) {
		super(pageSize);
		setDefaultKeyboardSelectionPolicy();
	}

	private void setDefaultKeyboardSelectionPolicy() {
		setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
	}

	public void showLoading() {
		setVisibleRangeAndClearData(getVisibleRange(), true);
		setRowCount(1);
	}
}
