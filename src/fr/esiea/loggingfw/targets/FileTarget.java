package fr.esiea.loggingfw.targets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;

public class FileTarget extends AbstractTarget {

	private long maxFileSize = 10000; //10000 octets par défaut

	private boolean activateRotation = getFileRotationProperty();
	private String titleFile;
	private File loggerFile;
	
	/**
	 * Pour forcer le fichier à se réécrire si on a choisit le mode rotatif, plutot que de créer un autre fichier.<p>
	 * Seulement lorsque le nom du fichier a été choisit par l'utilisateur.
	 */
	private boolean filePathSetManually = false;
	
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");

	@Override
	public void log(String pName, LoggerLevel level, String message, LoggerFormatter pFormatter) {

		if(activateRotation){
			if(titleFile == null)
			{
				createNewLoggerFile();
			}
			if(loggerFile != null){
				long sizeFile = loggerFile.length();
				if(sizeFile > maxFileSize){
					if(!filePathSetManually)
						createNewLoggerFile();
					else
						loggerFile.delete();
						
				}
			}
			loggerFile = new File(titleFile);
			titleFile = loggerFile.getName();
		}
		else 
		{
			try{
				loggerFile = new File(getLoggerFilePath());
			} catch (NullPointerException npe) {
				createNewLoggerFile();
				loggerFile = new File(titleFile);
			}
		}
		writeToLoggerFile(printLog(pName, level, message), loggerFile.getAbsolutePath());
	}

	
	private String printLog(String pName, LoggerLevel level, String message){
		return "[NAME:"+pName+
				" \tLEVEL:"+level.name()+
				" \tMESSAGE:"+message+"]\n";

	}
	
	private void createNewLoggerFile(){
		Date date = new Date();
		String titleDate = dt.format(date);
		File f = new File(titleDate+".txt");
		if(f.exists())
			titleDate = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss.SSS").format(date);
		titleFile = titleDate+".txt";
	}

	/**
	 * @param log le contenu du log
	 * @param filepath le chemin du fichier dans lequel nous allons logger
	 */
	private void writeToLoggerFile(String log, String filepath){
		if(filepath == null){
			filepath = getHomeFolderPathProperty() + File.separator + "Documents" + File.separator + "log.txt";
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
			System.out.println("Log : "+loggerFile.getAbsolutePath());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Renvoie le nom du fichier de log s'il existe, en crée un sinon à partir du fichier properties
	 * @return Le nom du fichier de log
	 * @throws NullPointerException : fichier de config non trouvé
	 */
	public String getLoggerFilePath() throws NullPointerException{
		
		if(titleFile == null){
			try{
				titleFile = getLoggerFilePathProperty();
			} catch (NullPointerException npe) {
				throw npe;
			} 
		}
		return titleFile;
	}

	/**
	 * @return Le nom du fichier de log renseigné dans le fichier properties.
	 * @throws NullPointerException : fichier de config non trouvé
	 */
	public String getLoggerFilePathProperty() throws NullPointerException{
		
		String property = null;
		try{
			property = ReadPropertiesFile.getProperty("logger.file.path"); 
		} catch (NullPointerException npe) {
			throw npe;
		} 
		return property;
	}


	public String getHomeFolderPathProperty(){
		return ReadPropertiesFile.getProperty("user.home");
	}

	public Boolean getFileRotationProperty(){
		return ReadPropertiesFile.getBooleanProperty("activate.file.rotation");
	}

	public void setMaxFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public void setActivateRotation(boolean activateRotation) {
		this.activateRotation = activateRotation;
	}
	

	/**
	 * @return the loggerFile
	 */
	public File getLoggerFile() {
		return loggerFile;
	}

	/**
	 * @param loggerFilePath le chemin du fichier de log désiré
	 */
	public void setLoggerFile(String loggerFilePath) {
		File f = new File(loggerFilePath);
		this.titleFile = loggerFilePath;
		this.loggerFile = f;
		this.filePathSetManually = true;
	}



}
