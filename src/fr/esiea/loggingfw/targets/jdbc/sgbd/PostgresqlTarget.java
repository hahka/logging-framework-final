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
	
	/**
	 * Creation de la table de log spécifique à Postgresql
	 */
	public void createLogTable() {
		
		Connection conn = null;
		try {
			conn = this.getConnection();
			if(!JdbcQuerys.tableExists(conn, "log"))
				JdbcQuerys.executeUpdate(conn, "CREATE SEQUENCE logs_sequence;");

			if(!JdbcQuerys.tableExists(conn, "log"))
				JdbcQuerys.executeUpdate(conn, "CREATE TABLE log "
					+ "(id INT NOT NULL DEFAULT nextval('logs_sequence') PRIMARY KEY, "
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
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
	
	/**
	 * Suppression de la table de log et de la sequence associée, puis re-création 
	 */
	public void resetDatabase() {
		
		Connection conn = null;
		try {
			conn = this.getConnection();
			if(JdbcQuerys.tableExists(conn, "log")){
				JdbcQuerys.executeUpdate(conn, "DROP TABLE log;");
			}
			if(JdbcQuerys.tableExists(conn, "logs_sequence")){
				JdbcQuerys.executeUpdate(conn, "DROP SEQUENCE logs_sequence;");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			createLogTable();
		}
		
	}

}
