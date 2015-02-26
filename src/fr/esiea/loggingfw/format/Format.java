package fr.esiea.loggingfw.format;

import fr.esiea.loggingfw.levels.LoggerLevel;

public class Format {
	
	public Format(){}
	
	public void format(String pName, LoggerLevel pLevel, String pMessage){
		System.out.println("[NAME: " + pName);
		System.out.println("->\tLEVEL: " + pLevel.name());
		System.out.println("->\tMESSAGE: " + pMessage+" ]\n");
	}
	
	public void formatWithDate(String pName, String pDate, LoggerLevel pLevel, String pMessage){
		System.out.println("[NAME: " + pName + " at "+ pDate);
		System.out.println("->\tLEVEL: " + pLevel.name());
		System.out.println("->\tMESSAGE: " + pMessage+" ]\n");
	}

}
