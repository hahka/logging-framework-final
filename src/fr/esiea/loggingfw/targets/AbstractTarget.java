package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

/* 
 * Classe AbstractTarget
 * Classe abstraite de base pour les cibles.
 * Pour créer de nouvelles cibles, elles doivent hériter de cette classe.
 */
public abstract class AbstractTarget {

	/*
	 * Seule fonction réellement utile à toutes les cibles, celle effectuant le log 
	 */
	public abstract void log(String name, LoggerLevel level, String message, LoggerFormatter formatter);

}
