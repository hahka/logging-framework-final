package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuerys {

	/**
	 * Exécute une mise à jour sur une table (exemple : ajout de table, insertion de données...)
	 * @param conn : la connexion à la base de donnée
	 * @param query : la requète que l'on souhaite effectuer
	 * @return true si la requête a réussi, false sinon
	 */
	public static boolean executeUpdate(Connection conn, String query) {

		boolean result = false;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			result = true;
		} catch (SQLException se) {
			// Catch des erreurs JDBC
			String errorCode = se.getSQLState();
			System.out.println(errorCode + " : " + se.getMessage());

		} catch (Exception e) {
			// Catch des erreurs Class.forName
			e.printStackTrace();
		} finally {
			// Le bloc try a réussi, on freme les ressources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}

		return result;
	}
	
	/**
	 * @param connection : la connexion à la base de donnée
	 * @param nomTable : le nom de la table dont on vérifie l'existence 
	 * @return true si la table existe, false sinon
	 * @throws SQLException
	 */
	public static boolean tableExists(Connection connection, String nomTable)
			throws SQLException {
		boolean existe;
		DatabaseMetaData dmd = connection.getMetaData();
		ResultSet tables = dmd.getTables(connection.getCatalog(), null,
				nomTable, null);
		existe = tables.next();
		tables.close();
		return existe;
	}

}
