package modell.entitaeten.factory;

import modell.entitaeten.implementierung.ImpFenster;
import modell.entitaeten.interfaces.Fenster;

public class FensterFactory {

	public static Fenster getInstance(){
		return new ImpFenster();
	}

}
