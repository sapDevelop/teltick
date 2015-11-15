package modell.factory;

import modell.implementierung.ImpHSqlDbZugriff;
import modell.interfaces.DBZugriff;

public class HSqlDbZugriffFactory {
	
	public static DBZugriff getInstance(){
		return new ImpHSqlDbZugriff();
	}

}
