package modell.factory;

import modell.implementierung.ImpRowMappingRecht;
import modell.interfaces.RowMappingRecht;

public class RowMappingRechtSingletonFactory {

	private static RowMappingRecht obj = null;
	public static RowMappingRecht getInstance(){
		if (obj == null) obj = new ImpRowMappingRecht();
		return obj;
	}
}
