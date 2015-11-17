package modell.factory;

import modell.implementierung.ImpRowMappingFenster;
import modell.interfaces.RowMappingFenster;

public class RowMappingFensterSingletonFactory {
	
	private static RowMappingFenster obj;
	
	public static RowMappingFenster getInstance(){
		if (obj == null) obj = new ImpRowMappingFenster();
		return obj;
	}

}
