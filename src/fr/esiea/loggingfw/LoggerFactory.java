package fr.esiea.loggingfw;

public final class LoggerFactory {
	
	public static OurLogger getLogger(Class pClass)
    {
        OurLogger logger = new OurLogger(pClass.getCanonicalName());
        
        return logger;
    }

}
