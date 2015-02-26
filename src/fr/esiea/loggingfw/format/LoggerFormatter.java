package fr.esiea.loggingfw.format;

import fr.esiea.loggingfw.levels.LoggerLevel;

public class LoggerFormatter {
	
	public void format(String pName, LoggerLevel pLevel, String pMessage){
		System.out.println("[NAME: " + pName);
		System.out.println("\t->LEVEL: " + pLevel.name());
		System.out.println("\t->MESSAGE: " + pMessage+" ]\n");
	}
	
	public void format(String pName, LoggerLevel pLevel, String pMessage, String pDate){
		System.out.println("[NAME: " + pName);
		System.out.println("\t->DATE: " + pDate);
		System.out.println("\t->LEVEL: " + pLevel.name());
		System.out.println("\t->MESSAGE: " + pMessage+" ]\n");
	}

}
