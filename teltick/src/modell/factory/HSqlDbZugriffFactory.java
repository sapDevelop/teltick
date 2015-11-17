package modell.factory;

import modell.implementierung.ImpHSqlDbZugriff;
import modell.interfaces.DBZugriff;

public class HSqlDbZugriffFactory {
	
	private static DBZugriff obj;
	public static DBZugriff getInstance(){
		if (obj == null) obj = new ImpHSqlDbZugriff();
		return obj;
	}

}
