package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.levels.LoggerLevel;

public class ConsoleTarget extends AbstractTarget {
	
	public ConsoleTarget(){
		super();
	}
	
	public void log(String pName, LoggerLevel pLevel, String pMessage){
		System.out.println("[NAME:"+pName+
				" LEVEL:"+pLevel.name()+
				" MESSAGE:"+pMessage+"]");
	}

}
