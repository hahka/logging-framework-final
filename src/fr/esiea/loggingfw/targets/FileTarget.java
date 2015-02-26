package fr.esiea.loggingfw.targets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class FileTarget extends AbstractTarget {

	private long maxFileSize = 10000; //10000 octets by default

	//private boolean activateRotation = getActivateFileRotation();
	private boolean activateRotation = true;
	private String titleFile;
	public File loggerFile;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH mm ss");

	@Override
	public void log(String pName, LoggerLevel level, String message) {
		// TODO Auto-generated method stub
		if(activateRotation){
			if(titleFile == null)
			{
				createNewLoggerFile();
			}
			if(loggerFile != null){
				long sizeFile = loggerFile.length();
				if(sizeFile > maxFileSize){
					createNewLoggerFile();			
				}
			}
			loggerFile = new File(titleFile);
			titleFile = loggerFile.getName();
		}
		else 
		{
			//loggerFile = new File(obtainLoggerFilePath());
			loggerFile = new File("C:\\Users\\Marie\\Desktop\\logs.txt");
		}
		writeToLoggerFile(printLog(pName, level, message), loggerFile.getAbsolutePath());
	}

	private String printLog(String pName, LoggerLevel level, String message){
		return "[NAME:"+pName+
				" LEVEL:"+level.name()+
				" MESSAGE:"+message+"]\n";

	}
	
	private void createNewLoggerFile(){
		Date date = new Date();
		String titleDate = dt.format(date);
		titleFile = titleDate  +".txt";
	}

	private void writeToLoggerFile(String log, String filepath){
		if(filepath == null){
			filepath = getHomeFolderPath() + File.separator + "Documents" + File.separator + "log.txt";
			System.out.println(filepath);
		}	
		try {
			if (!loggerFile.exists())
			{
				loggerFile.createNewFile();
			}
			FileWriter fw = new FileWriter(loggerFile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(log);
			bw.close();	
			System.out.println(loggerFile.getAbsolutePath());
			System.out.println("Log wrote");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public String obtainLoggerFilePath(){
		return ReadPropertiesFile.getProperty("logger.file.path");
	}

	public String getHomeFolderPath(){
		return ReadPropertiesFile.getProperty("user.home");
	}

	public Boolean getActivateFileRotation(){
		return Boolean.parseBoolean(ReadPropertiesFile.getProperty("activate.file.rotation"));
	}

	public void setMaxFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public void setActivateRotation(boolean activateRotation) {
		this.activateRotation = activateRotation;
	}


}
