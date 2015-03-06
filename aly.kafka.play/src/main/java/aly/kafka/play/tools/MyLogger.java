package aly.kafka.play.tools;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import aly.kafka.local.LocalConsumer;

public class MyLogger
{
	static public void main(String [] args)
	{
		Logger logger = createMyLogger();
		logger.info("info");
		logger.debug("debug");
		logger.warn("warn");
		logger.fatal("fatal");
	}
	
	public static Logger createMyLogger()
	{
		Logger myLogger = Logger.getLogger(MyLogger.class);
		FileAppender fileAppender = new FileAppender();
		fileAppender.setName("MyFileLogger");
		fileAppender.setFile("/Users/Proj/LunaJD/aly.kafka.play/Data/myLog.log");
		fileAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fileAppender.setThreshold(Level.DEBUG);
		fileAppender.setAppend(true);
		fileAppender.activateOptions();
		
		ConsoleAppender conAppender = new ConsoleAppender();
		conAppender.setName("MyConsoleAppender");
		conAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		conAppender.setThreshold(Level.WARN);
		conAppender.activateOptions();
		
		myLogger.setAdditivity(false);
		myLogger.addAppender(fileAppender);
		myLogger.addAppender(conAppender);
		return myLogger;
	}
}
