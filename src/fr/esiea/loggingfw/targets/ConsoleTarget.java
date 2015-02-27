package fr.esiea.loggingfw.targets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

/**
 * Classe pour logger en console
 */
public class ConsoleTarget extends AbstractTarget {
	
	public ConsoleTarget(){
		super();
		this.targetType = "console";
	}


	/**
	 * @see fr.esiea.loggingfw.targets.AbstractTarget#log(java.lang.String, fr.esiea.loggingfw.levels.LoggerLevel, java.lang.String, fr.esiea.loggingfw.format.LoggerFormatter)
	 */
	@Override
	public void log(String pName, LoggerLevel pLevel, String pMessage, LoggerFormatter pFormatter) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		System.out.println(pFormatter.format(pName, pLevel, pMessage, dateString));
		
	}

}
