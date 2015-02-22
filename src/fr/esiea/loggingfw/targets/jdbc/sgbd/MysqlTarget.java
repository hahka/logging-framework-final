package fr.esiea.loggingfw.targets.jdbc.sgbd;

import java.sql.SQLException;

import fr.esiea.loggingfw.targets.jdbc.JdbcQuerys;
import fr.esiea.loggingfw.targets.jdbc.JdbcTarget;

public class MysqlTarget extends JdbcTarget{

	public MysqlTarget(String pSgbd, String pDbUrl, String pUser, String pPass){
		super(pSgbd, pDbUrl, pUser, pPass);
		
		createLogTable();
	}
	

	public void createLogTable() {
		
		try {
			JdbcQuerys.executeUpdate(this, "DROP TRIGGER log_date_trigger;");
		
			JdbcQuerys.executeUpdate(this, "CREATE TRIGGER log_date_trigger "
						+ "BEFORE INSERT ON log "
						+ "FOR EACH ROW "
						+ "BEGIN "
						+ "SET NEW.date = NOW(); "
						+ "END;");
			
			if(!JdbcQuerys.tableExists(this.getConnection(), "log"))
				JdbcQuerys.executeUpdate(this, "CREATE TABLE log "
					+ "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,"
					+ "message VARCHAR(100),"
					+ "source VARCHAR(100), "
					+ "level INTEGER, "
					+ "date DATETIME);");
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
}
