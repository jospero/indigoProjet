package service;

import java.io.FileInputStream;
import java.util.Properties;


import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connect {
private static final String Class="driver.class.name";
private static final String Username="db.username";
private static final String URL="db.url";
private static final String PASSWORD="db.password";

private static Properties properties=null;
private static MysqlDataSource datasource;
static
{
	properties=new Properties();
	try {
		properties.load(new FileInputStream("src/service/database.properties"));
		datasource=new MysqlDataSource();
		datasource.setURL(properties.getProperty(URL));
		datasource.setUser(properties.getProperty(Username));
		datasource.setPassword(properties.getProperty(PASSWORD));
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}

public static DataSource getDatasource()
{
	return datasource;
}

}
