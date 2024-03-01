package com.ra.service.admin;

import com.ra.entity.Employee;
import com.ra.entity.Product;

public interface EmployeeService {
    Employee findId(String empId);
    String findName(String empName);
}
