package fr.esiea.loggingfw.targets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class FileTarget extends AbstractTarget {

	public void log(String pName, LoggerLevel level, String message) {
		writeToLoggerFile(printLog(pName, level, message), null);
	}

	@Override
	public void log(String pName, LoggerLevel level, String message,
			LoggerFormatter formatter) {
		writeToLoggerFile(printLog(pName, level, message), null);
	}
	
	private String printLog(String pName, LoggerLevel level, String message){
		return "[NAME:"+pName+
				" LEVEL:"+level.name()+
				" MESSAGE:"+message+"]\n";

	}
	
	private void writeToLoggerFile(String log, String path){
		if(path == null){
			path = getHomeFolderPath() + File.separator + "Documents" + File.separator + "file.txt";
			System.out.println(path);
		}		
		writeToLogger(log, path);
	}

	private void writeToLogger(String log, String filepath){
		try {
			File loggerFile = new File(filepath);
			if (!loggerFile.exists())
			{
				loggerFile.createNewFile();
			}
			FileWriter fw = new FileWriter(loggerFile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(log);
			bw.close();
			
			System.out.println("Log wrote");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		

	}

	public String obtainLoggerFilePath(){
		ReadPropertiesFile confProperty = new ReadPropertiesFile();
		confProperty.readProperties();
		Properties confFile = confProperty.config;
		return confFile.getProperty("logger.file.path");

	}

	public String getHomeFolderPath(){
		return System.getProperty("user.home");
	}



}
