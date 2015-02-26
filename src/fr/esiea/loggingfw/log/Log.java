package fr.esiea.loggingfw.log;

import fr.esiea.loggingfw.levels.LoggerLevel;

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
	

}
