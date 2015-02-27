package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

/** 
 * Classe AbstractTarget<p>
 * Classe abstraite de base pour les cibles.<p>
 * Pour créer de nouvelles cibles, elles doivent hériter de cette classe.
 */
public abstract class AbstractTarget {

	protected String targetType;
	
	/**
	 * log : Seule fonction réellement utile à toutes les cibles, celle effectuant le log 
	 * @param name : nom (source) du logger
	 * @param level : niveau de priorité des logs (DEBUG, INFO, ERREUR)
	 * @param message : le message qui va être loggé
	 * @param formatter : la mise en forme du message
	 */
	public abstract void log(String name, LoggerLevel level, String message, LoggerFormatter formatter);

	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((targetType == null) ? 0 : targetType.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTarget other = (AbstractTarget) obj;
		if (targetType == null) {
			if (other.targetType != null)
				return false;
		} else if (!targetType.equals(other.targetType))
			return false;
		return true;
	}

}
