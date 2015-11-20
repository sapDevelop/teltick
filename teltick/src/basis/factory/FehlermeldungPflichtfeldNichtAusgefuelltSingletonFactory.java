package basis.factory;

import basis.implementierung.ImpEingabePruefung;
import basis.interfaces.EingabePruefung;

public class FehlermeldungPflichtfeldNichtAusgefuelltSingletonFactory {

	private static EingabePruefung obj = null;
		
	public static EingabePruefung getInstance(){
		if (obj == null) obj = new ImpEingabePruefung();
		return obj;
	}

}
