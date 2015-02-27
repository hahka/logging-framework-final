package fr.esiea.loggingfw.format;

import fr.esiea.loggingfw.levels.LoggerLevel;

/**
 * Classe servant à formatter les logs
 */
public class LoggerFormatter {
	
	/**
	 * @param pName nom du logger (source, permet d'identifier le logger)
	 * @param pLevel niveau de priorité du logger
	 * @param pMessage message à logger
	 * @return Les informations du log mises en forme
	 */
	public String format(String pName, LoggerLevel pLevel, String pMessage){
		String result;
		result = "[NAME: " + pName;
		result += "\n\t->LEVEL: " + pLevel.name();
		result += "\n\t->MESSAGE: " + pMessage+" ]\n";
		
		return result;
	}
	
	/**
	 * @param pName nom du logger (source, permet d'identifier le logger)
	 * @param pLevel niveau de priorité du logger
	 * @param pMessage message à logger
	 * @param pDate date à laquelle le log a été effectué
	 * @return Les informations du log mises en forme
	 */
	public String format(String pName, LoggerLevel pLevel, String pMessage, String pDate){
		String result;
		result = "[NAME: " + pName;
		result += "\n\t->DATE: " + pDate;
		result += "\n\t->LEVEL: " + pLevel.name();
		result += "\n\t->MESSAGE: " + pMessage+" ]\n";
		
		return result;
	}

}
