package com.test.mymall.commons;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws Exception{
		Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        String jdbcDriver = "jdbc:mysql://localhost:3306/jjdev?useUnicode=true&characterEncoding=euckr";
        String dbID = "root";
        String dbPW = "java0000";
        connection = DriverManager.getConnection(jdbcDriver, dbID, dbPW);
        return connection;
	}
	
	public static void close(ResultSet resultset, Statement statement, Connection connection) {
		if(resultset != null) {
            try {resultset.close();} catch(Exception exception) {exception.printStackTrace();}
        }
        if(statement != null) {
            try {statement.close();} catch(Exception exception) {exception.printStackTrace();}
        }
        if(connection != null) {
            try {connection.close();} catch(Exception exception) {exception.printStackTrace();}
        }
	}
}
