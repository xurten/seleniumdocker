package com.database;

import java.sql.*;

public class MySqlRepository implements IRepository {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost";
    public static final String DB_URL_WITH_DB ="jdbc:mysql://localhost/logs";
    static final String USER = "root";
    static final String PASS = "root";

    @Override
    public void prepereDatabase() {
        executeUpdate("CREATE DATABASE logs;");
        executeUpdate("CREATE TABLE logs (id int, message VARCHAR(200), testName VARCHAR(50), currentDate datetime);", DB_URL_WITH_DB);
        executeUpdate("insert into logs(id, message, testName, currentDate) values (1, 'click', 'firstTest', '2019-10-13 19:20:00');", DB_URL_WITH_DB);
    }

    @Override
    public void executeUpdate(String query) {
        executeUpdate(query, DB_URL);
    }

    @Override
    public void executeUpdate(String query, String url) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, USER, PASS);
            stmt = conn.createStatement();
            int affectedRows = stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}