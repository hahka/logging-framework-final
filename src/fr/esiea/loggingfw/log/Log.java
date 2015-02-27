package fr.esiea.loggingfw.log;

import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

/**
 * Classe définissant l'objet Log<p> 
 * Utile lors de la récupération des logs en base de donnée par exemple
 *
 */
public class Log {
	
	private String name;
	private LoggerLevel level;
	private String message;
	private String date = null;

	public Log(String pName, LoggerLevel pLevel, String pMessage){
		this.name = pName;
		this.level = pLevel;
		this.message = pMessage;
	}

	public Log(String pName, LoggerLevel pLevel, String pMessage, String pDate){
		this.name = pName;
		this.level = pLevel;
		this.message = pMessage;
		this.date = pDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		LoggerFormatter formatter = new LoggerFormatter();
		if(date == null)
			return formatter.format(name, level, message);
		else
			return formatter.format(name, level, message, date);
	}
	
	
	

}
