package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.format.LoggerFormatter;
import fr.esiea.loggingfw.levels.LoggerLevel;
import fr.esiea.loggingfw.log.Log;

/**
 * Classe cible Jdbc de base. Gère les logs.
 *
 */
public class JdbcTarget extends AbstractJdbcTarget {

	public JdbcTarget() {
		super();

		JDBC_DRIVER = ReadPropertiesFile.getProperty("jdbc_driver");
		DB_URL = ReadPropertiesFile.getProperty("db_url");
		USER = ReadPropertiesFile.getProperty("user");
		PASS = ReadPropertiesFile.getProperty("pass");

	}

	public JdbcTarget(String pSgbdDriver, String pDbUrl, String pUsername,
			String pPassword) {
		super();

		JDBC_DRIVER = pSgbdDriver;
		DB_URL = pDbUrl;
		USER = pUsername;
		PASS = pPassword;
		
	}

	/** 
	 * @see fr.esiea.loggingfw.targets.AbstractTarget#log(java.lang.String, fr.esiea.loggingfw.levels.LoggerLevel, java.lang.String, fr.esiea.loggingfw.format.LoggerFormatter)
	 */
	@Override
	public void log(String pName, LoggerLevel pLevel, String pMessage, LoggerFormatter pFormatter) {

		try {
			if(JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this.getConnection(), "INSERT INTO log "
						+ "(message, source, level) "
						+ "VALUES ('"+pMessage+"', '"+pName+"', "+pLevel.ordinal()+");");
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/** 
	 * @see fr.esiea.loggingfw.targets.jdbc.AbstractJdbcTarget#getLogs()
	 */
	@Override
	public ArrayList<Log> getLogs(){
		
		ArrayList<Log> logs = new ArrayList<Log>();
		// Ressources nécessaires pour effectuer une requête SQL
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
	            int level = rs.getInt("LEVEL");
	            String date = rs.getString("DATE");
	            logs.add(new Log(source, LoggerLevel.values()[level], message, date));
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
		
		return logs;

	}

	
	/**
	 * @see fr.esiea.loggingfw.targets.jdbc.AbstractJdbcTarget#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException{
		
		Connection conn = null;
	    try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			throw e;
		}
	    return conn;
	}

}
