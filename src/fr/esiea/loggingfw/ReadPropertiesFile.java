package fr.esiea.loggingfw;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadPropertiesFile {
	public Properties config;

	public void readProperties(){
		config = new Properties();
		String filepath = ".//resources//config.properties";
		InputStream input = null;

		try
		{			
			input = new FileInputStream(filepath);
			config.load(input);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(input != null)
			{
				try
				{
					input.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public Properties getConfig() {
		return config;
	}

}



