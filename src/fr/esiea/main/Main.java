package fr.esiea.main;

import java.util.Properties;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.WriteLoggerFile;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		OurLogger logger = LoggerFactory.getLogger(Main.class);
		ReadPropertiesFile confProperty = new ReadPropertiesFile();
		confProperty.readProperties();
		Properties confFile = confProperty.config;
		String levelFromFile = confFile.getProperty("level");
		
		logger.d("blablabla1");
		logger.e("blablablaaaa1");
		
		logger.setLevel(LoggerLevel.DEBUG);
		
		logger.d("blablabla");
		logger.e("blablablaaaa");
		
		logger.setLevel(levelFromFile);
		logger.d("blablabla");
		logger.e("blablablaaaa");
		
		WriteLoggerFile wlg = new WriteLoggerFile();
		wlg.writeToLoggerFile(logger.writeLog(LoggerLevel.DEBUG, "test hihihi"));
		

		
	}

}
