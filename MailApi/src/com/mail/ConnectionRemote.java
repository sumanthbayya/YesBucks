package com.mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  abstract class ConnectionRemote {
	private static Connection con;
	static{
		try
		{
			String connectionUrl = "jdbc:sqlserver://den1.mssql6.gear.host;" +  
			         "databaseName=gspps;user=gspps;password=boogie123!@#"; 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(connectionUrl);
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

