package com.foxminded.andreimarkov.sqlschool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;

import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;

public class TestResourseLoader {
	
	private ConnectionJdbc connectionJdbc;

	public void create(String fileName) throws SQLException, IOException, URISyntaxException {
		runScript("src/test/resourses/" + fileName);
//		runScript(fileName);	
	}

	private Connection getConnection() throws IOException, URISyntaxException {
		connectionJdbc = new ConnectionJdbc();
		Connection connection = null;
		try {
			connection = connectionJdbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	private void runScript(String fileName) throws SQLException, IOException, URISyntaxException {
		
		Connection connection = null;
		RunScript runScript = new RunScript();
		try {
			connection = getConnection();		
			runScript.execute(connection, load(fileName));
		} finally {
			closeConnection(connection);
		}		
	}

	private static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("Failed to close connection", e);
			}
		}
	}
	
	private Reader load(String fileName) throws IOException {
		return new InputStreamReader(new FileInputStream(fileName), "utf-8");
	}
	
}
