package gov.cgn.sigcgn.client.lib.cellview;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;

/**
 * Clase base para todos los tipos de columnas de {@link CellTableExt}.
 * 
 * @author gusrod
 * 
 * @param <T>
 * @param <C>
 */
public abstract class ColumnExt<T, C> extends Column<T, C> {

	public ColumnExt(Cell<C> cell) {
		super(cell);
	}

	public ColumnExt(Cell<C> cell, CellTable<T> table, String header) {
		super(cell);
		addTo(header, table);
	}

	public ColumnExt<T, C> addTo(String header, CellTable<T> table) {
		table.addColumn(this, header);
		return this;
	}

	public ColumnExt<T, C> addTo(String header, CellTable<T>... tables) {
		for (CellTable<T> table : tables) {
			table.addColumn(this, header);
		}
		return this;
	}

}
