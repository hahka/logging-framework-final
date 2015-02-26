package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class ConsoleTarget extends AbstractTarget {
	
	public ConsoleTarget(){
		super();
	}


	@Override
	public void log(String pName, LoggerLevel pLevel, String pMessage, LoggerFormatter pFormatter) {
		
		pFormatter.format(pName, pLevel, pMessage);
		
	}

}
