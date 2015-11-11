package modell.factory;

import modell.implementation.HSqlDbZugriff;
import modell.interfaces.DBZugriff;

public class HSqlDbZugriffFactory {

	public static DBZugriff getInstance(){
		return new HSqlDbZugriff();
	}

}
