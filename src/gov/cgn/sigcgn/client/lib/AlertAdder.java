package gov.cgn.sigcgn.client.lib;

public interface AlertAdder {

	void addAlert(AlertType type, String strongText, String text, boolean autoCloses);

}
