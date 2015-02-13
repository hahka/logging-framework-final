package fr.esiea.loggingfw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class WriteLoggerFile {

	public void writeToLoggerFile(String log){		
		String path = obtainLoggerFilePath();
		writeToLoggerFile(log, path);
	}

	public void writeToLoggerFile(String log, String filepath){
		try {
			File loggerFile = new File(filepath);
			if (!loggerFile.exists())
			{
				loggerFile.createNewFile();
			}
			FileWriter fw = new FileWriter(loggerFile.getAbsoluteFile());
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
		return confFile.getProperty("loggerFilePath");

	}

}