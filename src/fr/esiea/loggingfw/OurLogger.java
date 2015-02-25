package fr.esiea.loggingfw;

import java.util.ArrayList;

import fr.esiea.loggingfw.targets.AbstractTarget;
import fr.esiea.loggingfw.targets.TargetFactory;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class OurLogger {
	
	/* TODO
	 * Ajouter une cible qui utilisera probablement une factory
	 * La fonction printlog utilisera cette cible et utilisera sa fonction print,
	 * qui sera definit en abstract dans la classe abstract Target
	 */

	private String name;
	private LoggerLevel level;
	private ArrayList<AbstractTarget> target;

	public OurLogger(String pName){
		
		this.name = pName;
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		
	}
	public OurLogger(Class pClass){
		
		this.name = pClass.getCanonicalName();
		this.level = LoggerLevel.ERROR;
		this.target = new ArrayList<AbstractTarget>();
		this.target.add(TargetFactory.getTarget("console"));
		
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
	
	private void printLog(LoggerLevel pLevel,String pMessage) {
		
		for(AbstractTarget t : target){
			t.log(name, pLevel, pMessage);
		}
		
		
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
	
	public void removeTarget(AbstractTarget pTarget){
		this.target.remove(pTarget);
	}

}

