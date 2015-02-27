package fr.esiea.loggingfw.targets.jdbc.sgbd;

import java.sql.Connection;
import java.sql.SQLException;

import fr.esiea.loggingfw.targets.jdbc.JdbcQuerys;
import fr.esiea.loggingfw.targets.jdbc.JdbcTarget;

public class MysqlTarget extends JdbcTarget{

	/**
	 * @param pSgbd : driver jdbc
	 * @param pDbUrl : url de la base de donnée
	 * @param pUser : utilisateur
	 * @param pPass : mot de passe
	 */
	public MysqlTarget(String pSgbd, String pDbUrl, String pUser, String pPass){
		super(pSgbd, pDbUrl, pUser, pPass);
	}
	
	/**
	 * Creation de la table log et du trigger associé pour la date, spécifique à MySql
	 */
	public void createLogTable() {
		
		Connection conn = null;
		try {
			conn = this.getConnection();
			JdbcQuerys.executeUpdate(conn, "DROP TRIGGER log_date_trigger;");
		
			JdbcQuerys.executeUpdate(conn, "CREATE TRIGGER log_date_trigger "
						+ "BEFORE INSERT ON log "
						+ "FOR EACH ROW "
						+ "BEGIN "
						+ "SET NEW.date = NOW(); "
						+ "END;");
			
			if(!JdbcQuerys.tableExists(conn, "log"))
				JdbcQuerys.executeUpdate(conn, "CREATE TABLE log "
					+ "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,"
					+ "message VARCHAR(100), "
					+ "source VARCHAR(100), "
					+ "level INTEGER, "
					+ "date DATETIME);");
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
