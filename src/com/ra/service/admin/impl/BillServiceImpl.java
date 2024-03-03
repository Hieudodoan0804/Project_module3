package com.ra.service.admin.impl;

import com.ra.entity.Bill;
import com.ra.entity.BillDetail;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.BillService;
import com.ra.util.Column;
import com.ra.util.Id;
import com.ra.util.MySqlConnect;
import com.ra.util.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BillServiceImpl implements BillService {
    private Repository<Bill, Integer> billRepository = new RepositoryImpl<>();

    @Override
    public Bill findId(Integer billId) {
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill.getBillId() == billId) {
            return bill;
        }
        return null;
    }

    @Override
    public int add(Bill entity) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            conn = MySqlConnect.open();
            String columns = Arrays.stream(fields).map(f -> f.getAnnotation(Column.class).name()).collect(Collectors.joining(","));
            String values = Arrays.stream(fields).map(f -> "?").collect(Collectors.joining(","));
            String sql = MessageFormat.format("INSERT INTO {0}({1}) VALUES ({2})", entity.getClass().getAnnotation(Table.class).name(), columns, values);
            int index = 1;
            String[] returnId = {"Bill_id"};
            pst = conn.prepareStatement(sql, returnId);
            for (Field f : fields) {
                f.setAccessible(true);
                pst.setObject(index++, f.get(entity));
            }
            pst.executeUpdate();
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnect.close(conn);
        }
        return 0;
    }

    @Override
    public List<BillDetail> getBillDetailByBillCode(String billCode) {
        Connection conn = null;
        PreparedStatement pst;
        ResultSet rs;
        List<BillDetail> billDetailList = new ArrayList<>();
        try {
            conn = MySqlConnect.open();
            String sql = "SELECT * FROM Bill AS B JOIN BILL_DETAIL AS BD ON B.BILL_iD = BD.BILL_Id WHERE B.Bill_Code = ? ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, billCode);
            rs = pst.executeQuery();
            while (rs.next()) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                billDetail.setBillId(rs.getInt("Bill_Id"));
                billDetail.setProductId(rs.getString("Product_Id"));
                billDetail.setQuantity(rs.getInt("Quantity"));
                billDetail.setPrice(rs.getFloat("Price"));
                billDetailList.add(billDetail);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnect.close(conn);
        }
        return billDetailList;
    }

    public String getBillStatusAsString(int billType) {
        switch (billType) {
            case 0:
                return "Tạo";
            case 1:
                return "Huỷ";
            case 2:
                return "Duyệt";
            default:
                return "Không xác định";
        }
    }
}
