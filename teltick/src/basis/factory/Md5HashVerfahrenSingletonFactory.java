package basis.factory;

import basis.implementierung.ImpMd5HashVerfahren;
import basis.interfaces.HashVerfahren;

public class Md5HashVerfahrenSingletonFactory {

	private static HashVerfahren md5 = null;
	
	public static HashVerfahren getInstance(){
		if (md5 == null) md5 = new ImpMd5HashVerfahren();
		return md5;
	}
}
