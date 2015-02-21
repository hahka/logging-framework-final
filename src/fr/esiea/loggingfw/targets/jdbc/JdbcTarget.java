package fr.esiea.loggingfw.targets.jdbc;

import fr.esiea.loggingfw.LoggerFactory;
import fr.esiea.loggingfw.OurLogger;
import fr.esiea.loggingfw.ReadPropertiesFile;
import fr.esiea.loggingfw.levels.LoggerLevel;
import fr.esiea.loggingfw.targets.AbstractTarget;
import fr.esiea.main.Main;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Properties;

public class JdbcTarget extends AbstractTarget {
	
	public JdbcTarget(){
		super();
		
		OurLogger logger = LoggerFactory.getLogger(JdbcTarget.class);
		logger.setLevel(LoggerLevel.DEBUG);
		
		if(createDatabase("log"))
			logger.d("log possible");
	}
	

	public static boolean tableExists(Connection connection, String nomTable) throws SQLException{
		boolean existe;
		DatabaseMetaData dmd = connection.getMetaData();
		ResultSet tables = dmd.getTables(connection.getCatalog(),null,nomTable,null);
		existe = tables.next();
		tables.close();
		return existe;	
	}
	
	
	public boolean createDatabase(String databaseName){
		
		boolean result = false;
		String JDBC_DRIVER = "org.postgresql.Driver";  
		String DB_URL = "jdbc:postgresql://localhost/";

		   //  Database credentials
		   String USER = "thibautvirolle";
		   String PASS = "";
		   
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Enregistrer le driver JDBC
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Ouvrir une connexion
		      System.out.println("Connexion au SGBD...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Exécuter une requète
		      System.out.println("Création de la base de données...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE DATABASE "+databaseName;
		      stmt.executeUpdate(sql);

		      System.out.println("Base de données créée avec succès...");
		      result = true;
		   }catch(SQLException se){
		      //Catch des erreurs JDBC
			   String errorCode = se.getSQLState();
			   if(errorCode.equals(ErrorCodes.DUPLICATE_DATABASE)){
				   System.out.println("La base de données existe déjà.");
				   result = true;
			   } else {
				   System.out.println(errorCode + " : " + se.getMessage());
				   //se.printStackTrace();
			   }
				   

		   }catch(Exception e){
		      //Catch des erreurs Class.forName
		      e.printStackTrace();
		   }finally{
		      // Le bloc try a réussi, on freme les ressources 
		      try{
		         if(stmt!=null) stmt.close();
		      }catch(SQLException se2){
		    	  se2.printStackTrace();
		      }
		      try{
		         if(conn!=null) conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   
		   return result;
	}
	
	@Override
	public void log(String pName, LoggerLevel level, String message) {
		// TODO Auto-generated method stub
		
		//createDatabase("test");
		/*String url = "jdbc:postgresql://localhost/";
		Properties props = new Properties();
		props.setProperty("user","thibautvirolle");
		props.setProperty("password","secret");
		props.setProperty("ssl","true");
		try {
			Connection conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*String connectionURL = "jdbc:postgresql://localhost:5432/movies;user=java;password=samples";
		// Change the connection string according to your db, ip, username and password
		
		try {
			 
		    // Load the Driver class. 
		    Class.forName("org.postgresql.Driver");
		    // If you are using any other database then load the right driver here.
		 
		    //Create the connection using the static getConnection method
		    //Connection con = DriverManager.getConnection (connectionURL);
		    Connection con = getConnection();
		 
		    //Create a Statement class to execute the SQL statement
		    Statement stmt = con.createStatement();
		 
		    //Execute the SQL statement and get the results in a Resultset
		    ResultSet rs = stmt.executeQuery("select moviename, releasedate from movies");
		 
		    // Iterate through the ResultSet, displaying two values
		    // for each row using the getString method
		 
		    while (rs.next())
		        System.out.println("Name= " + rs.getString("moviename") + " Date= " + rs.getString("releasedate"));
		    
		    con.close();
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
		finally {
		    // Close the connection
		    //con.close();
		}*/
		
	}
	

	/*private static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI("postgres://brgrspdyczzxep:B_SFAFvSib-ztnfR4hej5JiZgA@ec2-54-195-241-57.eu-west-1.compute.amazonaws.com:5432/ddrip20p99rq2a");

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}*/

}
