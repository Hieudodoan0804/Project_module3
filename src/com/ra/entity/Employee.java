package com.ra.entity;

import com.ra.util.Column;
import com.ra.util.Id;
import com.ra.util.Name;
import com.ra.util.Table;

import java.util.Date;
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "Emp_Id")
    private String empId;
    @Name
    @Column(name = "Emp_Name")
    private String empName;
    @Column(name = "Birth_Of_Date")
    private Date birthOfDate;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Address")
    private String address;
    @Column(name = "Emp_Status")
    private int empStatus;

    public Employee() {
    }

    public Employee(String empId, String empName, Date birthOfDate, String email, String phone, String address, int empStatus) {
        this.empId = empId;
        this.empName = empName;
        this.birthOfDate = birthOfDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.empStatus = empStatus;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }
}
