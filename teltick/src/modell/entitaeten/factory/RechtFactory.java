package modell.entitaeten.factory;

import modell.entitaeten.implementierung.ImpRecht;
import modell.entitaeten.interfaces.Recht;

public class RechtFactory {

	public static Recht getInstance(){
		return new ImpRecht();
	}

}
