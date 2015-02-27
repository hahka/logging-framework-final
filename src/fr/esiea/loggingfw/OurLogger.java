package fr.esiea.loggingfw;

import java.util.ArrayList;

import fr.esiea.loggingfw.targets.AbstractTarget;
import fr.esiea.loggingfw.targets.TargetFactory;
import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;


/**
 * Classe OurLogger<p>
 * Classe principale du logger<p>
 * params:<p>
 * 	name : pour différencier les différents logs. Généralement la source du log<p>
 * 	level : le niveau de log (debug, error...)<p>
 * 	target : la liste des cibles on va vouloir logger<p>
 * 	formatter : formatter utilisé pour mettre en forme les logs
 */
public class OurLogger {
	
	private String name;
	private LoggerLevel level;
	private ArrayList<AbstractTarget> target;
	private LoggerFormatter formatter;

	public OurLogger(String pName){
		
		this.name = pName;
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		this.formatter = new LoggerFormatter();
		
	}
	
	public OurLogger(Class<?> pClass){
		
		this.name = pClass.getCanonicalName();
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		this.formatter = new LoggerFormatter();
		
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

	public void i(String pMessage) { info(pMessage); }
	
	public void info(String pMessage) {
		if(level.ordinal() <= LoggerLevel.INFO.ordinal()){
			printLog(LoggerLevel.INFO,pMessage);
		}
	}
	
	/**
	 * Effectue les logs dans les différentes cibles du logger
	 * @param pLevel : niveau de priorité du log
	 * @param pMessage : message à logger
	 */
	private void printLog(LoggerLevel pLevel,String pMessage) {
		for(AbstractTarget t : target){
			t.log(name, pLevel, pMessage, formatter);
		}
	}
	

	
	/**
	 * @param pLevel : niveau de priorité du logger
	 */
	public void setLevel(LoggerLevel pLevel) {
		this.level = pLevel;
	}
	
	/**
	 * @param valueLevel : String correspondant au niveau de priorité du logger
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
	 * @param pTarget : String utilisée par TargetFactory pour retrouver la cible correspondant 
	 */
	public void setTarget(String pTarget){
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget(pTarget));
	}
	
	/**
	 * @param pTarget : cible (AbstractTarget) pour le logger
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
	 * @param pTarget : String utilisée par le TargetFactory pour ajouter au logger une cible
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
	 * @param target : cible à enlever
	 */
	public void removeTarget(AbstractTarget target){
		this.target.remove(target);
	}

}

