package modell.factory;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.implementierung.ImpRowMappingMitarbeiter;
import modell.interfaces.RowMappingBasis;

public class RowMappingMitarbeiterSingletonFactory {

	private static RowMappingBasis<Mitarbeiter> obj = null;
	
	public static RowMappingBasis<Mitarbeiter> getInstance(){
		if ( obj == null) obj = new ImpRowMappingMitarbeiter();
		return obj;
	}
}
