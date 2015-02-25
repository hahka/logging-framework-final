package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.esiea.loggingfw.levels.LoggerLevel;
import fr.esiea.loggingfw.targets.AbstractTarget;

public class JdbcTarget extends AbstractTarget {

	protected String JDBC_DRIVER = null;
	protected String DB_URL = null;
	protected String USER = null;
	protected String PASS = null;

	public JdbcTarget() {
		super();

		/*JDBC_DRIVER = "org.postgresql.Driver";
		DB_URL = "jdbc:postgresql://postgresql.alwaysdata.com:5432/hahka_logging_framework_db";
		USER = "hahka_logging_framework_user";
		PASS = "esiea@15";*/
	}

	public JdbcTarget(String pSgbdDriver, String pDbUrl, String pUsername,
			String pPassword) {
		super();

		JDBC_DRIVER = pSgbdDriver;
		DB_URL = pDbUrl;
		USER = pUsername;
		PASS = pPassword;
		
	}

	// Log en base de donnée
	@Override
	public void log(String pName, LoggerLevel pLevel, String pMessage) {

		try {
			if(JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "INSERT INTO log "
						+ "(message, source, level) "
						+ "VALUES ('"+pMessage+"', '"+pName+"', "+pLevel.ordinal()+");");
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// Récupération des logs insérés en base de donnée
	public void getLogs(){
		// Ressources néces
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = this.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM log;");
			
	        while (rs.next()) {
	            String message = rs.getString("MESSAGE");
	            String source = rs.getString("SOURCE");
	            String date = rs.getString("DATE");
	            System.out.println(source + "\t" + message + "\t" + date);
	        }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// Le bloc try a réussi, on ferme les ressources
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

	
	// Getters et Setters classiques
	public String getJdbcDriver() { return JDBC_DRIVER; }

	public void setJdbcDriver(String pJjdbcDriver) { JDBC_DRIVER = pJjdbcDriver; }

	public String getDbUrl() { return DB_URL; }

	public void setDbUrl(String pDbUrl) { DB_URL = pDbUrl; }

	public String getUser() { return USER; }

	public void setUser(String pUser) { USER = pUser; }

	public String getPass() { return PASS; }

	public void setPass(String pPass) { PASS = pPass; }


	// 
	public Connection getConnection() throws SQLException{
		
		Connection conn = null;
	    try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	    return conn;
	}

}
