package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.esiea.loggingfw.log.Log;
import fr.esiea.loggingfw.targets.AbstractTarget;

/* 
 * Classe AbstractJdbcTarget
 * Classe abstraite de base pour les cibles utilisant JDBC (base de données).
 * Pour créer de nouvelles cibles utilisant JDBC, elles doivent hériter de cette classe.
 * params:
 * 	JDBC_DRIVER : driver jdbc utilisé suivant que l'on utilise Postgresql, Mysql...
 * 	DB_URL : url de la base de données
 * 	USER : identifiant de l'utilisateur
 * 	PASS : mot de passe pour accéder à la base de données (optionnel)
 */
public abstract class AbstractJdbcTarget extends AbstractTarget {

	// Paramètres nécessaires à une connection à un sgbd
	protected String JDBC_DRIVER = null;
	protected String DB_URL = null;
	protected String USER = null;
	protected String PASS = null;

	
	// Récupération des logs insérés en base de donnée
	public abstract ArrayList<Log> getLogs();

	
	// Getters et Setters classiques
	public String getJdbcDriver() { return JDBC_DRIVER; }

	public void setJdbcDriver(String pJjdbcDriver) { JDBC_DRIVER = pJjdbcDriver; }

	public String getDbUrl() { return DB_URL; }

	public void setDbUrl(String pDbUrl) { DB_URL = pDbUrl; }

	public String getUser() { return USER; }

	public void setUser(String pUser) { USER = pUser; }

	public String getPass() { return PASS; }

	public void setPass(String pPass) { PASS = pPass; }


	/* 
	 * Fonction appelée avant chaque requête.
	 * Crée une connection avec le sgbd.
	 * A fermer une fois la requête exécutée.
	 */
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
