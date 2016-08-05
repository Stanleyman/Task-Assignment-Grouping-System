package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	public static Connection createConnection() throws SQLException, ClassNotFoundException
	{
		final String url="XXXX";
		final String user="XXXX";
		final String password="XXXX";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, user, password);
		return con;
	}
}
