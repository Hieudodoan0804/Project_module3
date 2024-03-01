package com.ra.entity;

import com.ra.util.Column;
import com.ra.util.Id;
import com.ra.util.Table;

import java.util.Date;
@Table(name = "BILL")
public class Bill {
    @Id
    @Column(name = "Bill_Id")
    private int billId;
    @Column(name = "Bill_Code")
    private String billCode;
    @Column(name = "Bill_Type")
    private boolean billType;
    @Column(name = "Emp_Id_Created")
    private String empIdCreated;
    @Column(name = "Created")
    private Date created;
    @Column(name = "Emp_Id_Auth")
    private String empIdAuth;
    @Column(name = "Auth_Date")
    private Date authDate;
    @Column(name = "Bill_Status")
    private int billStatus;

    public Bill() {
    }

    public Bill(int billId, String billCode, boolean billType, String empIdCreated, Date created, String empIdAuth, Date authDate, int billStatus) {
        this.billId = billId;
        this.billCode = billCode;
        this.billType = billType;
        this.empIdCreated = empIdCreated;
        this.created = created;
        this.empIdAuth = empIdAuth;
        this.authDate = authDate;
        this.billStatus = billStatus;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public boolean isBillType() {
        return billType;
    }

    public void setBillType(boolean billType) {
        this.billType = billType;
    }

    public String getEmpIdCreated() {
        return empIdCreated;
    }

    public void setEmpIdCreated(String empIdCreated) {
        this.empIdCreated = empIdCreated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmpIdAuth() {
        return empIdAuth;
    }

    public void setEmpIdAuth(String empIdAuth) {
        this.empIdAuth = empIdAuth;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }
}
