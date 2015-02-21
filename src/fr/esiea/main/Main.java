package fr.esiea.main;

import java.util.Properties;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.levels.LoggerLevel;
import fr.esiea.loggingfw.targets.jdbc.JdbcTarget;

public class Main {

	public static void main(String[] args) {
				
		OurLogger logger = LoggerFactory.getLogger(Main.class);
		ReadPropertiesFile confProperty = new ReadPropertiesFile();
		confProperty.readProperties();
		Properties confFile = confProperty.config;
		String levelFromFile = confFile.getProperty("level");
		
		logger.setLevel(LoggerLevel.DEBUG);
		//logger.setTarget("jdbc");

		logger.setTarget(new JdbcTarget("postgresql",
										"jdbc:postgresql://postgresql.alwaysdata.com:5432/hahka_logging_framework_db",
										"hahka",
										"23tivi03"));

		/*logger.setTarget(new JdbcTarget("mysql",
										"jdbc:mysql://mysql.alwaysdata.com:3306/hahka_logging_framework_db",
										"hahka",
										"23tivi03"));*/
		

		logger.d("debug1");
		/*logger.i("info1");
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
		logger.e("blablablaaaa");*/
		
	}

}
