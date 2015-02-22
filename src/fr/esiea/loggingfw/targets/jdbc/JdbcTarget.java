package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.levels.LoggerLevel;
import fr.esiea.loggingfw.targets.AbstractTarget;

public class JdbcTarget extends AbstractTarget {

	protected String JDBC_DRIVER;
	protected String DB_URL;
	protected String USER;
	protected String PASS;

	public JdbcTarget() {
		super();

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

		JDBC_DRIVER = JdbcDrivers.getDriver(pSgbd);
		DB_URL = pDbUrl;
		USER = pUsername;
		PASS = pPassword;
		
	}

	@Override
	public void log(String pName, LoggerLevel pLevel, String pMessage) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if(JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "INSERT INTO log "
						+ "(message, source, level) "
						+ "VALUES ('"+pMessage+"', '"+pName+"', "+pLevel.ordinal()+");");

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


	public Connection getConnection() throws SQLException{
		Connection conn = null;
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    return conn;
	};

}
