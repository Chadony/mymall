package com.test.mymall.commons;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBHelper {
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=euckr";
		String dbID = "root";
		String dbPW = "java0000";
		connection = DriverManager.getConnection(jdbcDriver, dbID, dbPW);
		return connection;
	}

	public static void close(ResultSet resultset, Statement statement, Connection connection) {
		if (resultset != null) {
			try {
				resultset.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public static SqlSession getSqlSession() {
		InputStream inputStream = null;
		try {
			String resource = "mybatis-config.xml";
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		sqlSession.commit();
		sqlSession.rollback();
		sqlSession.close();
		return sqlSession;
	}
}
