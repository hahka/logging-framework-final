package fr.esiea.loggingfw.targets.jdbc;

public class JdbcDrivers {

	public final static String POSTGRESQL_DRIVER = "org.postgresql.Driver";
	public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	
	
	public static String getDriver(String sgbd) {
		
		sgbd = sgbd.toLowerCase();
		if(sgbd.equals("postgresql")
				|| sgbd.equals("postgres"))
			return POSTGRESQL_DRIVER;
		else if(sgbd.equals("mysql"))
			return MYSQL_DRIVER;
		
		return null;
	}

}
