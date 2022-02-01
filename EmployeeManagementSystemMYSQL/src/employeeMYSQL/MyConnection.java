package employeeMYSQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
static Connection con;
public static Connection getMyConnection() throws SQLException, FileNotFoundException, IOException 
{
	if(con==null)
	{
	Properties property =new Properties();	
	property.load(new FileInputStream(new File("C:\\Users\\Harisha\\eclipse-workspace\\relationship\\src\\jdbcconnection\\db.properties")));
	String url=property.getProperty("url");
	String username=property.getProperty("username");
	String password=property.getProperty("password");
	
	con= DriverManager.getConnection(url,username,password);
	
	return con;

	
	}
	else {
		return con;
	}
	
}
}
