package com.foxminded.andreimarkov.sqlschool.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;

public class CreatorDataBase {
	
	public void createDB(ConnectionJdbc connectionJdbc) throws Exception {
		String fileName = "schema.sql";
		Connection conn = null;
		try {
			conn = connectionJdbc.getConnection();
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setAutoCommit(true);
			runner.setStopOnError(true);

			// Give the input file to Reader
			URL res = getClass().getClassLoader().getResource(fileName);
			File file = Paths.get(res.toURI()).toFile();
			BufferedReader br = new BufferedReader(new FileReader(file));

			// Execute script
			runner.runScript(br);
			runner.closeConnection();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
