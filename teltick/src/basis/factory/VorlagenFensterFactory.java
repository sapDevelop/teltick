package basis.factory;

import basis.implementierung.ImpVorlagenFenster;
import basis.interfaces.VorlagenFenster;

public class VorlagenFensterFactory {

	public static VorlagenFenster getInstance(){
		
		return new ImpVorlagenFenster();
	}

}
