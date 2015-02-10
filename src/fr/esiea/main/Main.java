package fr.esiea.main;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OurLogger logger = LoggerFactory.getLogger(Main.class);

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
		

		
	}

}
