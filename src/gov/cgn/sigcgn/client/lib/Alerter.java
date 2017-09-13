package gov.cgn.sigcgn.client.lib;


import gov.cgn.sigcgn.client.lib.ui.AlertWidget;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Presentador de alertas.
 * 
 * @author gusrod
 * 
 */
public class Alerter {

	public static void addAlert(AcceptsOneWidget container, AlertType type, String strongText, String text, boolean autoCloses) {
		final AlertWidget alert = new AlertWidget(type, strongText, text);
		container.setWidget(alert);
		setAutoclose(alert, autoCloses);
	}

	public static void addAlert(HasWidgets container, AlertType type, String strongText, String text, boolean autoCloses) {
		final AlertWidget alert = new AlertWidget(type, strongText, text);
		// Clear is required
		container.clear();
		container.add(alert);
		setAutoclose(alert, autoCloses);
	}

	private static void setAutoclose(AlertWidget alert, boolean autoCloses) {
		if (autoCloses) {
			scheduleClose(alert);
		}
	}

	private static void scheduleClose(final AlertWidget alert) {
		Timer t = new Timer() {
			@Override
			public void run() {
				alert.close();
			}
		};
		t.schedule(10000);
	}

}
