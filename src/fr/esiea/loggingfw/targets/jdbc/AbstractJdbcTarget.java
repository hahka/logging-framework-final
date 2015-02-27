package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.esiea.loggingfw.log.Log;
import fr.esiea.loggingfw.targets.AbstractTarget;

/** 
 * Classe AbstractJdbcTarget<p>
 * Classe abstraite de base pour les cibles utilisant JDBC (base de données).<p>
 * Pour créer de nouvelles cibles utilisant JDBC, elles doivent hériter de cette classe.<p>
 */
public abstract class AbstractJdbcTarget extends AbstractTarget {

	// Paramètres nécessaires à une connection à un sgbd
	/**
	 * Driver jdbc utilisé suivant que l'on utilise Postgresql, Mysql...<p>
	 */
	protected String JDBC_DRIVER = null;
	/**
	 * Url de la base de données
	 */
	protected String DB_URL = null;
	/**
	 * Identifiant de l'utilisateur
	 */
	protected String USER = null;
	/**
	 * Mot de passe pour accéder à la base de données
	 */
	protected String PASS = null;

	
	/**
	 * @return La liste des logs inscrits en base de données 
	 */
	public abstract ArrayList<Log> getLogs();

	
	public String getJdbcDriver() { return JDBC_DRIVER; }

	public void setJdbcDriver(String pJjdbcDriver) { JDBC_DRIVER = pJjdbcDriver; }

	public String getDbUrl() { return DB_URL; }

	public void setDbUrl(String pDbUrl) { DB_URL = pDbUrl; }

	public String getUser() { return USER; }

	public void setUser(String pUser) { USER = pUser; }

	public String getPass() { return PASS; }

	public void setPass(String pPass) { PASS = pPass; }

	/**
	 * Fonction appelée avant charque requète.<p>
	 * A fermer une fois la requète exécutée.
	 * @return La connexion avec le sgbd
	 * @throws SQLException : l'un des paramètres est null, il manque le mot de passe, etc...
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


	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DB_URL == null) ? 0 : DB_URL.hashCode());
		result = prime * result
				+ ((JDBC_DRIVER == null) ? 0 : JDBC_DRIVER.hashCode());
		result = prime * result + ((PASS == null) ? 0 : PASS.hashCode());
		result = prime * result + ((USER == null) ? 0 : USER.hashCode());
		return result;
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractJdbcTarget other = (AbstractJdbcTarget) obj;
		if (DB_URL == null) {
			if (other.DB_URL != null)
				return false;
		} else if (!DB_URL.equals(other.DB_URL))
			return false;
		if (JDBC_DRIVER == null) {
			if (other.JDBC_DRIVER != null)
				return false;
		} else if (!JDBC_DRIVER.equals(other.JDBC_DRIVER))
			return false;
		if (PASS == null) {
			if (other.PASS != null)
				return false;
		} else if (!PASS.equals(other.PASS))
			return false;
		if (USER == null) {
			if (other.USER != null)
				return false;
		} else if (!USER.equals(other.USER))
			return false;
		return true;
	}

}
