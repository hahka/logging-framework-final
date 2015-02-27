package fr.esiea.loggingfw;

import java.util.ArrayList;

import fr.esiea.loggingfw.targets.AbstractTarget;
import fr.esiea.loggingfw.targets.TargetFactory;
import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class OurLogger {
	
	/**
	 * Champ pour différencier les logger. En général la source du logger.
	 */
	private String name;
	/**
	 * Niveau de priorité du logger
	 */
	private LoggerLevel level;
	/**
	 * Liste des cibles du logger
	 */
	private ArrayList<AbstractTarget> target;
	/**
	 * Formatter gérant la mise en forme des logs
	 */
	private LoggerFormatter formatter;

	/**
	 * Constructeur du logger à partir d'une chaîne de caractères
	 * @param pName String pour nommer le logger
	 */
	public OurLogger(String pName){
		
		this.name = pName;
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		this.formatter = new LoggerFormatter();
		
	}
	
	/**
	 * Constructeur du logger à partir d'une classe
	 * @param pClass Classe pour nommer le logger
	 */
	public OurLogger(Class<?> pClass){
		
		this.name = pClass.getCanonicalName();
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		this.formatter = new LoggerFormatter();
		
	}

	/**
	 * @see #debug(String)
	 * @param pMessage Message à logger
	 */
	public void d(String pMessage) { debug(pMessage); }
	
	/**
	 * Fonction de log pour les niveaux de priorité DEBUG et inférieurs (INFO, ERREUR)
	 * @param pMessage Message à logger
	 */
	public void debug(String pMessage) {
		if(level.ordinal() <= LoggerLevel.DEBUG.ordinal()){
			printLog(LoggerLevel.DEBUG,pMessage);
		}
	}

	/**
	 * @see #error(String)
	 * @param pMessage Message à logger
	 */
	public void e(String pMessage) { error(pMessage); }

	/**
	 * Fonction de log pour les niveaux de priorité ERREUR
	 * @param pMessage Message à logger
	 */
	public void error(String pMessage) {
		if(level.ordinal() <= LoggerLevel.ERROR.ordinal()){
			printLog(LoggerLevel.ERROR,pMessage);
		}
	}

	/**
	 * @see #info(String)
	 * @param pMessage Message à logger
	 */
	public void i(String pMessage) { info(pMessage); }

	/**
	 * Fonction de log pour les niveaux de priorité INFO et inférieur (ERREUR)
	 * @param pMessage Message à logger
	 */
	public void info(String pMessage) {
		if(level.ordinal() <= LoggerLevel.INFO.ordinal()){
			printLog(LoggerLevel.INFO,pMessage);
		}
	}
	
	/**
	 * Effectue les logs dans les différentes cibles du logger
	 * @param pLevel niveau de priorité du log
	 * @param pMessage message à logger
	 */
	private void printLog(LoggerLevel pLevel,String pMessage) {
		for(AbstractTarget t : target){
			t.log(name, pLevel, pMessage, formatter);
		}
	}
	

	
	/**
	 * Modifie le niveau de priorité du logger
	 * @param pLevel niveau de priorité désiré
	 */
	public void setLevel(LoggerLevel pLevel) {
		this.level = pLevel;
	}
	
	/**
	 * Modifie le niveau de priorité du logger à partir d'une String
	 * @param valueLevel String correspondant au niveau de priorité désiré
	 */
	public void setLevel(String valueLevel) {
		LoggerLevel pLevel = LoggerLevel.valueOf(valueLevel);		
		this.level = pLevel;
	}
	
	
	public LoggerLevel getLevelFromValue(String valueLevel) {
		LoggerLevel pLevel = LoggerLevel.valueOf(valueLevel);		
		return pLevel;
	}
	
	/**
	 * Définit une cible unique pour le logger à partir d'une chain de caractères
	 * @param pTarget String utilisée par TargetFactory pour retrouver la cible correspondant 
	 */
	public void setTarget(String pTarget){
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget(pTarget));
	}
	
	/**
	 * Définit une cible unique pour le logger à partir d'une AbstractTarget ou ses classe filles
	 * @param pTarget cible (AbstractTarget) pour le logger
	 */
	public void setTarget(AbstractTarget pTarget){
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(pTarget);
	}
	
	/**
	 * Ajout d'une cible seulement si elle n'est pas déjà présente<p>
	 * Utilise les .equals surchargés pour tester la présence
	 * @param pTarget : AstractTarget à ajouter au logger
	 */
	public void addTarget(AbstractTarget pTarget){
		if(! this.target.contains(pTarget))
			this.target.add(pTarget);
	}

	/**
	 * Ajout d'une cible (par son nom) seulement si elle n'est pas déjà présente<p>
	 * Utilise les .equals surchargés pour tester la présence
	 * @param pTarget String utilisée par le TargetFactory pour ajouter au logger une cible
	 */
	public void addTarget(String pTarget){
		AbstractTarget target = TargetFactory.getTarget(pTarget);
		if(! this.target.contains(target))
			this.target.add(target);
	}
	
	/**
	 * Enlève toutes les cibles associées
	 */
	public void removeTargets(){
		this.target = new ArrayList<AbstractTarget>();
	}
	
	/**
	 * Enlève la cible passée en paramètre
	 * @param target cible à enlever
	 */
	public void removeTarget(AbstractTarget target){
		this.target.remove(target);
	}
	
	/**
	 * Modifie le formatter associé au logger
	 * @param pFormatter Nouveau formatter pour afficher les logs
	 */
	public void setFormatter(LoggerFormatter pFormatter){
		this.formatter = pFormatter;
	}

}

