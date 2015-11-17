package modell.factory;

import modell.implementierung.ImpRowMappingMitarbeiter;
import modell.interfaces.RowMappingMitarbeiter;

public class RowMappingMitarbeiterSingletonFactory {

	private static RowMappingMitarbeiter obj = null;
	
	public static RowMappingMitarbeiter getInstance(){
		if ( obj == null) obj = new ImpRowMappingMitarbeiter();
		return obj;
	}
}
