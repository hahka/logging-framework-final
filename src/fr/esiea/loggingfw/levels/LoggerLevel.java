package fr.esiea.loggingfw.levels;

public enum LoggerLevel {
	DEBUG("DEBUG"), 
	INFO("INFO"), 
	ERROR("ERROR");
	
	private String valueLevel;

	LoggerLevel(String level){
		this.setValueLevel(level);
	}

	public String getValueLevel() {
		return valueLevel;
	}

	public void setValueLevel(String valueLevel) {
		this.valueLevel = valueLevel;
	}
}

