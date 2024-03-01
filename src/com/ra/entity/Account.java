package com.ra.entity;

import com.ra.util.Column;
import com.ra.util.Id;
import com.ra.util.Name;
import com.ra.util.Table;

@Table(name = "ACCOUNT")
public class Account {
    @Id
    @Column(name = "Acc_Id")
    private int accId;
    @Name
    @Column(name = "User_Name")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "Permission")
    private boolean permission;
    @Column(name = "Emp_Id")
    private String empId;
    @Column(name = "Acc_Status")
    private boolean accStatus;

    public Account() {
    }

    public Account(int accId, String userName, String password, boolean permission, String empId, boolean accStatus) {
        this.accId = accId;
        this.userName = userName;
        this.password = password;
        this.permission = permission;
        this.empId = empId;
        this.accStatus = accStatus;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public boolean isAccStatus() {
        return accStatus;
    }

    public void setAccStatus(boolean accStatus) {
        this.accStatus = accStatus;
    }
}
