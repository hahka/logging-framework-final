package fr.esiea.main;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OurLogger logger = LoggerFactory.getLogger(Main.class);

		logger.d("blablabla1");
		logger.e("blablablaaaa1");
		
		logger.setLevel(LoggerLevel.DEBUG);
		
		logger.d("blablabla");
		logger.e("blablablaaaa");
		

		
	}

}
