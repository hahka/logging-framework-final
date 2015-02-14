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
<<<<<<< HEAD
		ReadPropertiesFile confProperty = new ReadPropertiesFile();
		confProperty.readProperties();
		Properties confFile = confProperty.config;
		String levelFromFile = confFile.getProperty("level");
		
		logger.d("blablabla1");
		logger.e("blablablaaaa1");
=======

		logger.d("debug1");
		logger.i("info1");
		logger.e("error1");
>>>>>>> c1d08d9b567c6921135e792ed42e599d38352c55
		
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
		
		WriteLoggerFile wlg = new WriteLoggerFile();
		wlg.writeToLoggerFile(logger.writeLog(LoggerLevel.DEBUG, "test hihihi"));
		

		
	}

}
