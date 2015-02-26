package fr.esiea.loggingfw;

import java.util.ArrayList;

import fr.esiea.loggingfw.targets.AbstractTarget;
import fr.esiea.loggingfw.targets.TargetFactory;
import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;


/*
 * Classe OurLogger
 * Classe principale du logger
 * params:
 * 	name : pour différencier les différents logs. Généralement la source du log
 * 	level : le niveau de log (debug, error...)
 * 	target : la liste des cibles on va vouloir logger
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
	
	public OurLogger(Class pClass){
		
		this.name = pClass.getCanonicalName();
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		this.formatter = new LoggerFormatter();
		
	}

	/*
	 * Fonctions de debug
	 */
	public void d(String pMessage) { debug(pMessage); }
	
	public void debug(String pMessage) {
		if(level.ordinal() <= LoggerLevel.DEBUG.ordinal()){
			printLog(LoggerLevel.DEBUG,pMessage);
		}
	}

	/*
	 * Fonctions d'erreur
	 */
	public void e(String pMessage) { error(pMessage); }
	
	public void error(String pMessage) {
		if(level.ordinal() <= LoggerLevel.ERROR.ordinal()){
			printLog(LoggerLevel.ERROR,pMessage);
		}
	}

	/*
	 * Fonctions d'info
	 */
	public void i(String pMessage) { info(pMessage); }
	
	public void info(String pMessage) {
		if(level.ordinal() <= LoggerLevel.INFO.ordinal()){
			printLog(LoggerLevel.INFO,pMessage);
		}
	}
	
	/*
	 * Fonction qui effectuera le log dans les différentes cibles du logger
	 */
	private void printLog(LoggerLevel pLevel,String pMessage) {
		
		for(AbstractTarget t : target){
			t.log(name, pLevel, pMessage, formatter);
		}
		
	}
	

	/*
	 * Getters et setters pour le niveau de log
	 */
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
	
	/*
	 * Fonctions pour ajouter des cibles ou n'en définir qu'une seule
	 */
	public void setTarget(String pTarget){
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget(pTarget));
	}
	
	public void setTarget(AbstractTarget pTarget){
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(pTarget);
	}
	
	public void addTarget(AbstractTarget pTarget){
		this.target.add(pTarget);
	}
	
	public void removeTargets(){
		this.target = new ArrayList<AbstractTarget>();
	}

}

