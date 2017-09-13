package gov.cgn.sigcgn.client.lib.cellview;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;

public abstract class TextColumnExt<T> extends ColumnExt<T, String> {

	public TextColumnExt() {
		super(new TextCell());
	}

	public TextColumnExt(CellTable<T> table, String header) {
		super(new TextCell(), table, header);
	}

}
