package com.mail;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public  abstract class ConnectionProvider {
		private static Connection con;
		static{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jpa","jpa");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		public static Connection getConnection()
		{
			return con;
		}
	}


