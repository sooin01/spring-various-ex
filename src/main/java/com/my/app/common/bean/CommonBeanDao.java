package com.my.app.common.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommonBeanDao {

	@Autowired
	@Qualifier("dataSource2")
	private DataSource dataSource;

	@PostConstruct
	public void init() throws SQLException {
		System.out.println(dataSource);

		Connection c = dataSource.getConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("select now()");
		if (rs.next()) {
			System.out.println(rs.getString(1));
		}
		rs.close();
		s.close();
		c.close();
	}

}
