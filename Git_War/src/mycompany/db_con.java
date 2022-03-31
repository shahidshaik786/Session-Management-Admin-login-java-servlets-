package mycompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface db_con {
	public static Connection mydb_con() throws ClassNotFoundException {
		String dbName = "jdbc:postgresql://192.168.0.100:5432/stocks";
	    String userName = "stock22";
	    String password = "123456"; 
	    try {
	    Class.forName("org.postgresql.Driver");
	    Connection con = DriverManager.getConnection(dbName, userName, password);
		System.out.println("Data base connection created successfully");
	    return con;
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
		return null;
	}
	

}
