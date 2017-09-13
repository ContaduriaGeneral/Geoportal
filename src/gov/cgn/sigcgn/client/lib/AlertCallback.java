package gov.cgn.sigcgn.client.lib;

//import gov.cgn.integ.entities.exceptions.RpcValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Implementación de {@link AsyncCallback} usada en todos los llamados GWT-RPC
 * de la aplicación.
 * 
 * <p>
 * Se encarga del manejo de errores de servidor, de validación y de perdida de
 * sesión, presentando mensajes informativos al usuario usando {@link Alerter}.
 * </p>
 * 
 * @author gusrod
 * 
 * @param <T>
 */
public class AlertCallback<T> implements AsyncCallback<T>, AlertAdder {

	private HasWidgets alertContainer;
	private AcceptsOneWidget alertContainerOne;
	private SuccessCommand<T> command;

	public AlertCallback(HasWidgets alertContainer, SuccessCommand<T> command) {
		this.alertContainer = alertContainer;
		this.command = command;
	}

	public AlertCallback(AcceptsOneWidget alertContainer, SuccessCommand<T> command) {
		this.alertContainerOne = alertContainer;
		this.command = command;
	}

	@Override
	public void onFailure(Throwable caught) {
		if (caught instanceof StatusCodeException) {
			if (401 == ((StatusCodeException) caught).getStatusCode()) {
				Window.Location.replace(GWT.getHostPageBaseURL() + "login");
				return;
			}
		} 
//		else if (caught instanceof RpcValidationException) {
//			handleValidationException((RpcValidationException) caught);
//			return;
//		}

		addAlert(AlertType.danger, "Ha ocurrido un error,", "intente nuevamente.", false);
		caught.printStackTrace();
	}

//	protected void handleValidationException(RpcValidationException exception) {
//		addAlert(AlertType.warning, null, exception.getMessage(), true);
//	}

	@Override
	public void onSuccess(T result) {
		command.onSuccess(result, this);
	}

	@Override
	public void addAlert(AlertType type, String strongText, String text, boolean autoCloses) {
		if (null != alertContainer) {
			Alerter.addAlert(alertContainer, type, strongText, text, autoCloses);
		} else {
			Alerter.addAlert(alertContainerOne, type, strongText, text, autoCloses);
		}
	}

	/**
	 * Contrato para el delegado de {@link #onSuccess}.
	 * 
	 * @author gusrod
	 * 
	 * @param <T>
	 */
	public interface SuccessCommand<T> {
		void onSuccess(T result, AlertAdder adder);
	}

}
