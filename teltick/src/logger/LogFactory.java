package logger;

import org.apache.log4j.Logger;

public class LogFactory {
	
	public static Logger getInstance(String className) {
		return  Logger.getLogger(className);
	}
}

