package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnect {
    public static Connection open(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/WAREHOUSE";
            String use = "root";
            String password = "08041997";
            Connection conn = DriverManager.getConnection(url,use,password);
            return conn;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public static void close(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
