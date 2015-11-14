package modell.factory;

import modell.implementierung.ImpDaoMitarbeiter;
import modell.interfaces.DaoMitarbeiter;

public class DaoMitarbeiterFactory {

	public static DaoMitarbeiter getInstance(){
		return new ImpDaoMitarbeiter();
	}

}
