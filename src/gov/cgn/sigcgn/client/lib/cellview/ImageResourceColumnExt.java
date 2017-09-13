package gov.cgn.sigcgn.client.lib.cellview;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;

public class ImageResourceColumnExt<T> extends ColumnExt<T, ImageResource> {

	private ImageResource imageResource;

	public ImageResourceColumnExt(ImageResource imageResource, CellTable<T> table) {
		this(imageResource, table, null);
	}

	public ImageResourceColumnExt(ImageResource imageResource, CellTable<T> table, String header) {
		super(new ImageResourceCellExt(), table, header);
		this.imageResource = imageResource;
	}

	@Override
	public ImageResource getValue(T object) {
		return imageResource;
	}

}
