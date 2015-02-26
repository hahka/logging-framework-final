package fr.esiea.loggingfw;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public final class ReadPropertiesFile
{
	/** The Constant CONFIG_FILENAME. */
	private static final String   CONFIG_FILENAME 		 = "resources/config";      //$NON-NLS-1$
	public static ResourceBundle config = loadProperties();
	private static Locale currentLang = Locale.FRANCE;

	private ReadPropertiesFile()
	{
	}

//	public static Properties loadProperties(){		
//		InputStream input = null;
//		try {
//
//			input = new FileInputStream("CONFIG_FILENAME");
//			// load a properties file
//			prop.load(input);
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return prop;
//	}
//
//	public static String getProperty(String key) {
//		// TODO Auto-generated method stub
//		if (prop.containsKey(key))
//		{
//			return prop.getProperty(key);
//		}
//		else 
//		{
//			return null;
//		}
//
//	}


	public static ResourceBundle loadProperties(){
		return ResourceBundle.getBundle(CONFIG_FILENAME, currentLang);				
	}
	
	
		/**
		 * Renvoie la valeur d'une propriété.
		 * 
		 * @param propertyName Le nom de la propriété.
		 * @return la valeur de la propriété demandée
		 */
		public static Integer getIntProperty(final String propertyName)
		{
			final String property = getProperty(propertyName);
			return property == null ? null : Integer.parseInt(property);
		}
	
	
		/**
		 * Renvoie la valeur d'une propriété donnée.
		 * 
		 * @param propertyName Le nom de la propriété.
		 * @return la valeur de la propriété demandée.
		 */
		public static String getProperty(final String propertyName)
		{
			String property = null;
			if (config.containsKey(propertyName))
			{
				property = config.getString(propertyName);
			}
			return property;
		}
		
		public static String getConfigFilename() {
			return CONFIG_FILENAME;
		}
}