package gov.cgn.sigcgn.client.lib.cellview;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.CellTable;

public abstract class NumberColumnExt<T> extends ColumnExt<T, Number> {

	public NumberColumnExt(CellTable<T> table, String header) {
		super(new NumberCell(), table, header);
	}

}
