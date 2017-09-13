package gov.cgn.sigcgn.client.lib.cellview;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;

public abstract class DateColumnExt<T> extends TextColumnExt<T> {

	private DateTimeFormat dateTimeFormat;

	public DateColumnExt(CellTable<T> table, String header, DateTimeFormat dateTimeFormat) {
		super(table, header);
		this.dateTimeFormat = dateTimeFormat;
	}

	@Override
	public String getValue(T object) {
		return dateTimeFormat.format(getDate(object));
	}

	protected abstract Date getDate(T object);

}
