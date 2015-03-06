package aly.kafka.play.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class MyLogger2
{
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:ss:SSS");
	
	static public void main(String [] args)
	{
		Logger logger = createMyLogger(null, Level.DEBUG);   //ALL, INFO, ...
		logger.info("info");
		logger.debug("debug");
		logger.warn("warn");
		logger.fatal("fatal");
	}
	
	private static String LOGFILE = "/Users/Proj/LunaJD/aly.kafka.play/Data/myLog.log";
	
	public static Logger createMyLogger(String filePath, Level aThreshold)				// can be null
	{
		String logFile = (filePath == null ? LOGFILE : filePath);		// we don't check that file exists
		Level threshold = (aThreshold == null ? Level.INFO : aThreshold);
		
		Logger myLogger = Logger.getLogger(MyLogger2.class);
		FileAppender fileAppender = new FileAppender();
		fileAppender.setName("MyFileLogger");
		fileAppender.setFile(logFile);
		fileAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fileAppender.setThreshold(threshold);
		fileAppender.setAppend(true);
		fileAppender.activateOptions();
		
		ConsoleAppender conAppender = new ConsoleAppender();
		conAppender.setName("MyConsoleAppender");
		conAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		conAppender.setThreshold(threshold);
		conAppender.activateOptions();
		
		myLogger.setAdditivity(false);
		myLogger.addAppender(fileAppender);
		myLogger.addAppender(conAppender);
		
		Date now = new Date();
		String sTime = DATE_FORMAT.format(now);
		myLogger.info("\n\n++++++++++++ NEW TRACE =============== " + sTime + "\n");
		
		return myLogger;
	}
}
