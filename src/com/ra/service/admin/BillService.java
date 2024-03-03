package com.ra.service.admin;

import com.ra.entity.Bill;
import com.ra.entity.BillDetail;
import com.ra.util.Column;
import com.ra.util.MySqlConnect;
import com.ra.util.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface BillService {
    Bill findId(Integer billId);
    public int  add(Bill entity);

    List<BillDetail> getBillDetailByBillCode(String billCode);

}
