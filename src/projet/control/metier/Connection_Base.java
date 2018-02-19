package projet.control.metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_Base {
	
	private static Connection connection;
	
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/indigo","root","jospin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getconnection()
	{
		return connection;
	}

}
