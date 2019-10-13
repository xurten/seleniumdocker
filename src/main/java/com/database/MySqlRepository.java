package com.database;

import java.sql.*;

public class MySqlRepository implements IRepository {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/logs";
    static final String USER = "root";
    static final String PASS = "root";

    @Override
    public void prepereDatabase() {
        executeQuery("CREATE DATABASE logs;");
        executeQuery("CREATE DATABASE logs;");
        executeQuery("USE logs;");
        executeQuery("CREATE TABLE logs (id int, message VARCHAR(200), testName VARCHAR(50), currentDate datetime);");
        executeQuery("insert into logs(id, message, testName, currentDate) values (1, 'click', 'firstTest', '2019-10-13 19:20:00');");
    }

    @Override
    public void executeQuery(String query) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.close();
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
