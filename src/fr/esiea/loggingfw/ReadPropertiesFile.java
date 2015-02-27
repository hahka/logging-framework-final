package fr.esiea.loggingfw;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ReadPropertiesFile {
	
	private static String CONFIG_FILENAME = "resources/config"; //$NON-NLS-1$
	private static Locale currentLang = Locale.FRANCE;
	public static ResourceBundle config = loadProperties();

	public static ResourceBundle loadProperties(){
		ResourceBundle rb = null;
		try{
			rb = ResourceBundle.getBundle(CONFIG_FILENAME, currentLang);
		} catch(MissingResourceException e){
			System.out.println(e.getMessage());
		}
		return rb;
	}

	/**
	 * Renvoie la valeur d'une propriete.
	 * 
	 * @param propertyName
	 *            Le nom de la propriete.
	 * @return la valeur de la propriete demandee
	 */
	public static Integer getIntProperty(final String propertyName) {
		final String property = getProperty(propertyName);
		return property == null ? null : Integer.parseInt(property);
	} 
	/**
	 * Renvoie la valeur d'une propriete.
	 * 
	 * @param propertyName
	 *            Le nom de la propriete.
	 * @return la valeur de la propriete demandee
	 */
	public static boolean getBooleanProperty(final String propertyName) {
		boolean property = false;
		try {
			if (config.containsKey(propertyName)) {
				if(config.getString(propertyName).contains("true"))
					property = true;
			}
		} catch(NullPointerException npe){}
		return property;
	}

	/**
	 * Renvoie la valeur d'une propriete donnee.
	 * 
	 * @param propertyName
	 *            Le nom de la propriete.
	 * @return la valeur de la propriete demandee.
	 */
	public static String getProperty(final String propertyName) {
		String property = null;
		try {
			if (config.containsKey(propertyName)) {
				property = config.getString(propertyName);
			}
		} catch(NullPointerException npe){}
		return property;
	}

	public static String getConfigFilename() {
		return CONFIG_FILENAME;
	}
}