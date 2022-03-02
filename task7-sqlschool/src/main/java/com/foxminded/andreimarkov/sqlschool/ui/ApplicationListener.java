package com.foxminded.andreimarkov.sqlschool.ui;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;
import com.foxminded.andreimarkov.sqlschool.dao.CreatorDataBase;

@SuppressWarnings("serial")
public class ApplicationListener extends HttpServlet implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ConnectionJdbc connectionJdbc;
		CreatorDataBase runnerSchema;
		// create DB schema
		try {
			Class.forName("org.postgresql.Driver");
			connectionJdbc = new ConnectionJdbc();
			runnerSchema = new CreatorDataBase();
			runnerSchema.createDB(connectionJdbc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
