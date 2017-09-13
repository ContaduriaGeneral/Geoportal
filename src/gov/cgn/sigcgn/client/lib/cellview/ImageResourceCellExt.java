package gov.cgn.sigcgn.client.lib.cellview;

import static com.google.gwt.dom.client.BrowserEvents.CLICK;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;

public class ImageResourceCellExt extends ImageResourceCell {

	@Override
	public Set<String> getConsumedEvents() {
		Set<String> set = new HashSet<String>();
		set.add(CLICK);
		return set;
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context, Element parent, ImageResource value, NativeEvent event,
			ValueUpdater<ImageResource> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		if (CLICK.equals(event.getType())) {
			EventTarget eventTarget = event.getEventTarget();
			if (!Element.is(eventTarget)) {
				return;
			}
			if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
				// Ignore clicks that occur outside of the main element.
				onEnterKeyDown(context, parent, value, event, valueUpdater);
			}
		}
	}

	@Override
	protected void onEnterKeyDown(Context context, Element parent, ImageResource value, NativeEvent event, ValueUpdater<ImageResource> valueUpdater) {
		if (valueUpdater != null) {
			valueUpdater.update(value);
		}
	}
}
