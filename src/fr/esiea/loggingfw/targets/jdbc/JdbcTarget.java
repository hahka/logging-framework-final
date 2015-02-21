package fr.esiea.loggingfw.targets.jdbc;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.levels.LoggerLevel;
import fr.esiea.loggingfw.targets.AbstractTarget;
import fr.esiea.main.Main;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Properties;

public class JdbcTarget extends AbstractTarget {

	private String SGBD;
	private String JDBC_DRIVER;
	private String DB_URL;
	private String USER;
	private String PASS;

	public JdbcTarget() {
		super();

		OurLogger logger = LoggerFactory.getLogger(JdbcTarget.class);
		logger.setLevel(LoggerLevel.DEBUG);

		SGBD = "postgresql";
		JDBC_DRIVER = JdbcDrivers.getDriver("postgresql");
		DB_URL = "jdbc:postgresql://postgresql.alwaysdata.com:5432/hahka_logging_framework_db";
		USER = "hahka_logging_framework_user";
		PASS = "esiea@15";
	}

	public JdbcTarget(String pSgbd, String pDbUrl, String pUsername,
			String pPassword) {
		super();

		OurLogger logger = LoggerFactory.getLogger(JdbcTarget.class);
		logger.setLevel(LoggerLevel.DEBUG);

		SGBD = pSgbd;
		JDBC_DRIVER = JdbcDrivers.getDriver(pSgbd);
		DB_URL = pDbUrl;
		USER = pUsername;
		PASS = pPassword;
		
	}


	public boolean createDatabase(String databaseName) {

		boolean result = false;

		// Database credentials

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Enregistrer le driver JDBC
			Class.forName(JDBC_DRIVER);

			// STEP 3: Ouvrir une connexion
			System.out.println("Connexion au SGBD...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Exécuter une requète
			System.out.println("Création de la base de données...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE " + databaseName;
			stmt.executeUpdate(sql);

			System.out.println("Base de données créée avec succès...");
			result = true;
		} catch (SQLException se) {
			// Catch des erreurs JDBC
			String errorCode = se.getSQLState();
			if (errorCode.equals(ErrorCodes.DUPLICATE_DATABASE)) {
				System.out.println("La base de données existe déjà.");
				result = true;
			} else {
				System.out.println(errorCode + " : " + se.getMessage());
				// se.printStackTrace();
			}

		} catch (Exception e) {
			// Catch des erreurs Class.forName
			e.printStackTrace();
		} finally {
			// Le bloc try a réussi, on freme les ressources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public void log(String pName, LoggerLevel level, String message) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if(JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "INSERT INTO log "
						+ "(message, source) "
						+ "VALUES ('"+message+"', '"+pName+"');");

			conn = this.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM log;");
			
	        while (rs.next()) {
	            String coffeeName = rs.getString("MESSAGE");
	            String supplierID = rs.getString("SOURCE");
	            System.out.println(coffeeName + "\t" + supplierID);
	        }
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Le bloc try a réussi, on freme les ressources
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) { se.printStackTrace(); }
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { se.printStackTrace(); }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) { se.printStackTrace(); }
		}
		
	}


	public void createLogTable(String pName, LoggerLevel level, String message) {
		// TODO Auto-generated method stub

		try {
			if(!JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "CREATE SEQUENCE LogsSequence;");

			if(!JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "CREATE TABLE LOG "
					+ "(id INT NOT NULL DEFAULT nextval('LogsSequence') PRIMARY KEY ,"
					+ "message VARCHAR(100),"
					+ "source VARCHAR(100));");
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public Connection getConnection() throws SQLException {

	    Connection conn = null;
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    System.out.println("Connecté à la base de données");
	    return conn;
	}

}
