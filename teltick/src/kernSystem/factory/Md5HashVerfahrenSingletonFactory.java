package kernSystem.factory;

import kernSystem.implementierung.ImpMd5HashVerfahren;
import kernSystem.interfaces.HashVerfahren;

public class Md5HashVerfahrenSingletonFactory {

	private static HashVerfahren md5 = null;
	
	public static HashVerfahren getInstance(){
		if (md5 == null) md5 = new ImpMd5HashVerfahren();
		return md5;
	}
}
