package com.ra.service.admin.impl;


import com.ra.util.MySqlConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ReportServiceImpl {
   public static void callStatCostByDate(String date){
       Connection conn = null;
       try {
           conn = MySqlConnect.open();
           String query = "{call StatCostByDate(?)}";
           CallableStatement cs = conn.prepareCall(query);
           cs.setString(1,date);
           ResultSet rs = cs.executeQuery();
           if (rs.next()) {
               System.out.println("Tổng chí phí " + date + ": " + rs.getDouble(1));
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }finally {
           MySqlConnect.close(conn);
       }
   }
}
