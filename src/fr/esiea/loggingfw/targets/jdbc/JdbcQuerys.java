package fr.esiea.loggingfw.targets.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuerys {

	public static boolean executeUpdate(JdbcTarget target, String query) {

		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = target.getConnection();
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
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return result;
	}
	

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


	public static ResultSet executeQuery(JdbcTarget target, String query) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = target.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
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
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return rs;
	}

}
