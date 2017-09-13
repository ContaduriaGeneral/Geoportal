package gov.cgn.sigcgn.shared.util;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.SuggestOracle;

public class BuscarEntidadOracle extends SuggestOracle {

	private LinkedList<BuscarEntidadSuggestion> fStore;
	BuscarEntidadSuggestion entidadSuggestion;

	public BuscarEntidadOracle(List<InformacionEntidadSIG> listaEntidades) {
		fStore = new LinkedList<BuscarEntidadSuggestion>();

		for (InformacionEntidadSIG entidad : listaEntidades) {
			fStore.add(new BuscarEntidadSuggestion(entidad.getId_entidad(),
					entidad.getRazon_social().toUpperCase()));
		}
	}
	
		
	@Override
	public void requestSuggestions(Request request, Callback callback) {

		String query = request.getQuery();
		LinkedList<BuscarEntidadSuggestion> result = new LinkedList<BuscarEntidadSuggestion>();
		for (BuscarEntidadSuggestion entry : fStore) {
			if (entry.getDisplayString().contains(query.toUpperCase())
					&& result.size() < 20 ) {
				result.add(entry);
			} else {
				if (quitarTilde(entry.getDisplayString()).contains(
						query.toUpperCase())
						&& result.size() < 20) {
					result.add(entry);
				}
			}

		}
		callback.onSuggestionsReady(request, new Response(result));

	}

	private static String quitarTilde(String entrada) {

		String original = "áéíóúÁÉÍÓÚ";
		String ascii = "aeiouAEIOU";
		String salida = entrada;
		for (int i = 0; i < original.length(); i++) {

			salida = salida.replace(original.charAt(i), ascii.charAt(i));
		}
		return salida;
	}
}
