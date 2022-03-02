package com.foxminded.andreimarkov.sqlschool.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.foxminded.andreimarkov.sqlschool.dao.readers.ResourseReader;

public class ConnectionJdbc {

	private static String url;
	private static String user;
	private static String password;
	private Properties properties;

	public ConnectionJdbc() throws IOException, URISyntaxException {
		ResourseReader loader = new ResourseReader();
		properties = loader.readProperties();
		this.url = properties.getProperty("db.host");
		this.user = properties.getProperty("db.login");
		this.password = properties.getProperty("db.password");
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

}
