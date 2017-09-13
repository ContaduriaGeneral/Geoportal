package gov.cgn.sigcgn.client.lib.ui;

import gov.cgn.sigcgn.client.lib.AlertType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AlertWidget extends Composite {

	private static AlertWidgetUiBinder uiBinder = GWT.create(AlertWidgetUiBinder.class);

	interface AlertWidgetUiBinder extends UiBinder<Widget, AlertWidget> {
	}

	@UiField
	HTMLPanel panel;

	@UiField
	Element strongSpan;

	@UiField
	Element textSpan;

	public AlertWidget(AlertType type, String strongText, String text) {
		initWidget(uiBinder.createAndBindUi(this));
		if (!AlertType.none.equals(type)) {
			panel.addStyleName("alert-" + type);
		}
		strongSpan.setInnerText(strongText);
		textSpan.setInnerText(text);
	}

	public void close() {
		closeNat(panel.getElement());
	}

	private native void closeNat(Element elem) /*-{
		$wnd.jQuery(elem).alert('close');
	}-*/;

}
