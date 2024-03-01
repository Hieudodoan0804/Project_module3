package com.ra.service.admin.impl;

import com.ra.entity.Employee;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    private Repository<Employee,String> employeeRepository = new RepositoryImpl<>();
    @Override
    public Employee findId(String empId) {
        Employee employee = employeeRepository.findId(empId, Employee.class);
        if (employee.getEmpId().equals(empId)){
            return employee;
        }
        return null;
    }

    @Override
    public String findName(String empName) {
        Employee employee = employeeRepository.findName(empName, Employee.class);
        return employee.getEmpName();
    }
    public String getStatusString(int status) {
        switch (status) {
            case 0:
                return "Hoạt động";
            case 1:
                return "Nghỉ chế độ";
            case 2:
                return "Nghỉ việc";
            default:
                return "Không xác định";
        }
    }
}
