package modell.factory;


import modell.implementierung.ImpRowMappingTicketzuweisung;
import modell.interfaces.RowMappingTicketzuweisung;

public class RowMappingTicketzuweisungSingletonFactory {

	private static RowMappingTicketzuweisung obj = null;
	public static RowMappingTicketzuweisung getInstance(){
		if (obj == null) obj = new ImpRowMappingTicketzuweisung();
		return obj;
	}
}
