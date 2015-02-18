package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.levels.LoggerLevel;

public abstract class AbstractTarget {
	
	public abstract void log(String name, LoggerLevel level, String message);

}
