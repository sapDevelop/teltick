package modell.entitaeten.factory;

import modell.entitaeten.implementierung.ImpTicketzuweisung;
import modell.entitaeten.interfaces.Ticketzuweisung;

public class TicketzuweisungFactory {
	public static Ticketzuweisung  getInstance(){
		return new ImpTicketzuweisung();
	}
}
