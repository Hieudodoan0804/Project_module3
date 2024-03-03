package com.ra.service.admin.impl;


import com.ra.service.admin.ReportService;
import com.ra.util.MySqlConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ReportServiceImpl implements ReportService {
    @Override
    public void CostByDate() {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call CostByDate()}";
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Year_Month_Date | Total Cost |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String yearMonthDate = rs.getString("Year_Month_Date");
                float cost = rs.getFloat("Total_Cost");

                System.out.format("| %-15s | %-10.2f |\n", yearMonthDate, cost);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void CostByTimeRange(String startDate, String endDate) {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call CostByTimeRange(?,?)}";
            CallableStatement cs = conn.prepareCall(query);
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Year_Month_Date | Total Cost |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String yearMonthDate = rs.getString("Year_Month_Date");
                float cost = rs.getFloat("Total_Cost");

                System.out.format("| %-15s | %-10.2f |\n", yearMonthDate, cost);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void RevenueByDate() {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call RevenueByDate()}";
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            System.out.format("| Date | Revenue |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String Date = rs.getString("Date");
                float Revenue = rs.getFloat("Revenue");

                System.out.format("| %-15s | %-10.2f |\n", Date, Revenue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void RevenueByTimeRange(String startDate,String endDate) {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call RevenueByTimeRange(?,?)}";
            CallableStatement cs = conn.prepareCall(query);
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Date | Revenue |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String Date = rs.getString("Date");
                float Revenue = rs.getFloat("Revenue");

                System.out.format("| %-15s | %-10.2f |\n", Date, Revenue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void EmployeeStatusCount() {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call EmployeeStatusCount()}";
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Employee_Status | Employee_Count |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String employeeStatus = rs.getString("Employee_Status");
                int count = rs.getInt("Employee_Count");

                System.out.format("| %-15s | %-13d |\n", employeeStatus, count);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void MostImportedProduct(String startDate,String endDate) {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call MostImportedProduct(?,?)}";
            CallableStatement cs = conn.prepareCall(query);
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Product_Name | Total_Import_Quantity |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String productName = rs.getString("Product_Name");
                int totalImportQuantity = rs.getInt("Total_Import_Quantity");

                System.out.format("| %-15s | %-13d |\n", productName, totalImportQuantity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void LeastImportedProduct(String startDate,String endDate) {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call LeastImportedProduct(?,?)}";
            CallableStatement cs = conn.prepareCall(query);
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Product_Name | Total_Import_Quantity |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String productName = rs.getString("Product_Name");
                int totalImportQuantity = rs.getInt("Total_Import_Quantity");

                System.out.format("| %-15s | %-13d |\n", productName, totalImportQuantity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void MostExportedProduct(String startDate,String endDate) {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call MostExportedProduct(?,?)}";
            CallableStatement cs = conn.prepareCall(query);
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Product_Name | Total_Export_Quantity |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String productName = rs.getString("Product_Name");
                int totalExportQuantity = rs.getInt("Total_Export_Quantity");

                System.out.format("| %-15s | %-13d |\n", productName, totalExportQuantity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }

    @Override
    public void LeastExportedProduct(String startDate,String endDate) {
        Connection conn = null;
        try {
            conn = MySqlConnect.open();
            String query = "{call LeastExportedProduct(?,?)}";
            CallableStatement cs = conn.prepareCall(query);
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            ResultSet rs = cs.executeQuery();
            System.out.println("| Product_Name | Total_Export_Quantity |");
            System.out.println("+----------------+------------+");

            while (rs.next()) {
                String productName = rs.getString("Product_Name");
                int totalExportQuantity = rs.getInt("Total_Export_Quantity");

                System.out.format("| %-15s | %-13d |\n", productName, totalExportQuantity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySqlConnect.close(conn);
        }
    }
}
