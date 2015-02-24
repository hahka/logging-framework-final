package fr.esiea.loggingfw.targets.jdbc.sgbd;

import java.sql.SQLException;

import fr.esiea.loggingfw.targets.jdbc.JdbcQuerys;
import fr.esiea.loggingfw.targets.jdbc.JdbcTarget;

public class PostgresqlTarget extends JdbcTarget{
	
	public PostgresqlTarget(String pSgbd, String pDbUrl, String pUser, String pPass){
		super(pSgbd, pDbUrl, pUser, pPass);
		
		createLogTable();
	}
	
	
	public void createLogTable() {
		
		try {
			
			if(!JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "CREATE SEQUENCE logs_sequence;");

			if(!JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "CREATE TABLE log "
					+ "(id INT NOT NULL DEFAULT nextval('LogsSequence') PRIMARY KEY, "
					+ "message VARCHAR(100), "
					+ "source VARCHAR(100), "
					+ "level INTEGER, "
					+ "date TIMESTAMP default CURRENT_TIMESTAMP);");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void resetDatabase() {
		
		try {
			if(!JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "DROP TABLE log;");
			JdbcQuerys.executeUpdate(this, "DROP SEQUENCE logs_sequence;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
