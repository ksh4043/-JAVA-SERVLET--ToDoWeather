package service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	// DataBase Connection
	public Connection connectDB() throws IOException, SQLException, ClassNotFoundException {
		// session에 들어있는 상태값(관리자 or 일반 사용자)에 따라 properties 파일 다르게 읽기 후처리
		InputStream fis = getClass().getClassLoader().getResourceAsStream("DB_ADMIN.properties");
		Properties dbProp = new Properties();
		dbProp.load(fis);
		
		String dbDriver = dbProp.getProperty("db.driver");
		String dbURL = dbProp.getProperty("db.url");
		String dbUserName = dbProp.getProperty("db.username");
		String dbPassword = dbProp.getProperty("db.password");
		
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
		
		return con;
	}

}
