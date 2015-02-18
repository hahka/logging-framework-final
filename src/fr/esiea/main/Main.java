package fr.esiea.main;

import java.util.Properties;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class Main {

	public static void main(String[] args) {
				
		OurLogger logger = LoggerFactory.getLogger(Main.class);
		ReadPropertiesFile confProperty = new ReadPropertiesFile();
		confProperty.readProperties();
		Properties confFile = confProperty.config;
		String levelFromFile = confFile.getProperty("level");
		

		logger.d("debug1");
		logger.i("info1");
		logger.e("error1");
		
		logger.setLevel(LoggerLevel.INFO);

		logger.d("debug2");
		logger.i("info2");
		logger.e("error2");
		
		logger.setLevel(LoggerLevel.DEBUG);

		logger.d("debug3");
		logger.i("info3");
		logger.e("error3");
		
		logger.setLevel(levelFromFile);
		logger.d("blablabla");
		logger.e("blablablaaaa");
		
	}

}
