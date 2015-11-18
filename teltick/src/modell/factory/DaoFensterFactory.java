package modell.factory;

import modell.implementierung.ImpDaoFenster;
import modell.interfaces.DaoFenster;

public class DaoFensterFactory {

	public static DaoFenster getInstance(){
		return new ImpDaoFenster();
	}

}
