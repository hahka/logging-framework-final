package fr.esiea.loggingfw.targets.jdbc.sgbd;

import java.sql.Connection;
import java.sql.SQLException;

import fr.esiea.loggingfw.targets.jdbc.JdbcQuerys;
import fr.esiea.loggingfw.targets.jdbc.JdbcTarget;

public class PostgresqlTarget extends JdbcTarget{
	
	public PostgresqlTarget(String pSgbd, String pDbUrl, String pUser, String pPass){
		super(pSgbd, pDbUrl, pUser, pPass);
		
		createLogTable();
	}
	
	
	public void createLogTable() {
		
		Connection conn = null;
		try {
			conn = this.getConnection();
			if(!JdbcQuerys.tableExists(conn, "log"))
				JdbcQuerys.executeUpdate(conn, "CREATE SEQUENCE logs_sequence;");

			if(!JdbcQuerys.tableExists(conn, "log"))
				JdbcQuerys.executeUpdate(conn, "CREATE TABLE log "
					+ "(id INT NOT NULL DEFAULT nextval('LogsSequence') PRIMARY KEY, "
					+ "message VARCHAR(100), "
					+ "source VARCHAR(100), "
					+ "level INTEGER, "
					+ "date TIMESTAMP default CURRENT_TIMESTAMP);");
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
	
	public void resetDatabase() {
		
		Connection conn = null;
		try {
			conn = this.getConnection();
			if(!JdbcQuerys.tableExists(conn, "log"))
				JdbcQuerys.executeUpdate(conn, "DROP TABLE log;");
			JdbcQuerys.executeUpdate(conn, "DROP SEQUENCE logs_sequence;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
