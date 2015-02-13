package fr.esiea.loggingfw;

import fr.esiea.loggingfw.levels.LoggerLevel;

public class OurLogger {

	private String name;
	private LoggerLevel level;
	
	public OurLogger(String pName){
		
		this.name = pName;
		this.level = LoggerLevel.ERROR;
		
	}

	public void d(String pMessage) { debug(pMessage); }
	
	public void debug(String pMessage) {
		
		if(level.ordinal() <= LoggerLevel.DEBUG.ordinal()){
			printLog(LoggerLevel.DEBUG,pMessage);
		}
		
	}

	public void e(String pMessage) { error(pMessage); }
	
	public void error(String pMessage) {
		
		if(level.ordinal() <= LoggerLevel.ERROR.ordinal()){
			printLog(LoggerLevel.ERROR,pMessage);
		}
		
	}
	
	private void printLog(LoggerLevel pLevel,String pMessage) {
		
		System.out.println("[NAME:"+name+
				" LEVEL:"+pLevel.name()+
				" MESSAGE:"+pMessage+"]");
		
	}
	
	public String writeLog(LoggerLevel pLevel,String pMessage){
		return "[NAME:"+name+
				" LEVEL:"+pLevel.name()+
				" MESSAGE:"+pMessage+"]";
	}

	public void setLevel(LoggerLevel pLevel) {
		this.level = pLevel;
	}
	
	public void setLevel(String valueLevel) {
		LoggerLevel pLevel = LoggerLevel.valueOf(valueLevel);		
		this.level = pLevel;
	}
	
	public LoggerLevel getLevelFromValue(String valueLevel) {
		LoggerLevel pLevel = LoggerLevel.valueOf(valueLevel);		
		return pLevel;
	}
}

