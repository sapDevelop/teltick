package modell.entitaeten.factory;

import modell.entitaeten.implementierung.ImpMitarbeiter;
import modell.entitaeten.interfaces.Mitarbeiter;

public class MitarbeiterFactory {

	public static Mitarbeiter getInstance(){
		return new ImpMitarbeiter();
	}

}
