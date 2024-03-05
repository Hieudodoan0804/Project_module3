package com.ra.management;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.entity.Product;
import com.ra.model.ContStatus;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.impl.AccountServiceImpl;
import com.ra.service.admin.impl.EmployeeServiceImpl;
import com.ra.util.Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    static EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    public static void employeeManagement() {
        boolean isExist = true;
        do {
            System.out.println("******************EMPLOYEE MANAGEMENT****************");
            System.out.println("1.Danh sách nhân viên");
            System.out.println("2.Thêm mới nhân viên");
            System.out.println("3.Cập nhật thông tin nhân viên");
            System.out.println("4.Cập nhật trạng thái nhân viên");
            System.out.println("5.Tìm kiếm nhân viên");
            System.out.println("6.Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    displayEmployeeList();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    updateEmployeeStatus();
                    break;
                case 5:
                    searchEmployee();
                    break;
                case 6:
                    isExist = false;
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (isExist);
    }

    public static void displayEmployeeList() {
        Repository<Employee, String> employeeRepository = new RepositoryImpl<>();
        List<Employee> employeeList = employeeRepository.findAll(Employee.class);

        if (!employeeList.isEmpty()) {
            System.out.println("Danh sách nhân viên:");
            System.out.format("%-15s%-30s%-15s%-35s%-15s%-35s%-15s\n",
                    "Mã nhân viên", "Tên nhân viên", "Ngày sinh",
                    "Email", "Số điện thoại", "Địa chỉ", "Trạng thái");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            employeeList.stream().sorted(Comparator.comparing(Employee::getEmpName))
                    .forEach(employee -> System.out.format("%-15s%-30s%-15s%-35s%-15s%-35s%-15s\n",
                            employee.getEmpId(), employee.getEmpName(),
                            dateFormat.format(employee.getBirthOfDate()), employee.getEmail(),
                            employee.getPhone(), employee.getAddress(),
                            employeeService.getStatusString(employee.getEmpStatus())));
        } else {
            System.out.println("Không có nhân viên nào trong danh sách.");
        }
    }

    public static void addEmployee() {
        Repository<Employee, String> employeeRepository = new RepositoryImpl<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Nhập số nhân viên cần nhập thông tin:");
        int numberOfEmployees = Integer.parseInt(Console.scanner.nextLine());
        for (int i = 0; i < numberOfEmployees; i++) {
            Employee employee = new Employee();
            System.out.println("Nhập mã nhân viên:");
            String id = Console.scanner.nextLine();
            employee.setEmpId(id);
            System.out.println("Nhập tên nhân viên:");
            String name = Console.scanner.nextLine();
            employee.setEmpName(name);
            System.out.println("Nhập ngày sinh (yyyy-MM-dd):");
            String birthDateString = Console.scanner.nextLine();
            try {
                Date birthDate = dateFormat.parse(birthDateString);
                employee.setBirthOfDate(birthDate);
            } catch (ParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
                continue;
            }
            System.out.println("Nhập Email:");
            String email = Console.scanner.nextLine();
            employee.setEmail(email);
            System.out.println("Nhập số điện thoại:");
            String phone = Console.scanner.nextLine();
            employee.setPhone(phone);
            System.out.println("Nhập địa chỉ:");
            String address = Console.scanner.nextLine();
            employee.setAddress(address);
            System.out.println("Nhập trạng thái");
            int status = Integer.parseInt(Console.scanner.nextLine());
            employee.setEmpStatus(status);
            employeeRepository.add(employee);
            System.out.println("Thêm mới nhân viên thành công!");
        }
    }

    public static void updateEmployee() {
        Repository<Employee, String> employeeRepository = new RepositoryImpl<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Nhập vào mã nhân viên cần cập nhật thông tin:");
        String empId = Console.scanner.nextLine();
        Employee employeeUpdate = employeeService.findId(empId);
        if (employeeUpdate == null) {
            System.out.println("Không tìm thấy nhân viên!");
        } else {
            try {
                System.out.println("Nhập tên nhân viên:");
                String name = Console.scanner.nextLine();
                employeeUpdate.setEmpName(name);
                System.out.println("Nhập ngày sinh (yyyy-MM-dd):");
                String birthDateString = Console.scanner.nextLine();
                Date birthDate = dateFormat.parse(birthDateString);
                employeeUpdate.setBirthOfDate(birthDate);
                System.out.println("Nhập Email:");
                String email = Console.scanner.nextLine();
                employeeUpdate.setEmail(email);
                System.out.println("Nhập số điệ thoại:");
                String phone = Console.scanner.nextLine();
                employeeUpdate.setPhone(phone);
                System.out.println("Nhập trạng thái");
                int status = Integer.parseInt(Console.scanner.nextLine());
                employeeUpdate.setEmpStatus(status);
                employeeRepository.edit(employeeUpdate);
                System.out.println("Cập nhật nhân viên thành công!");
            } catch (ParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
            } catch (NumberFormatException e) {
                System.out.println("Trạng thái không hợp lệ. Vui lòng nhập một số nguyên.");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi cập nhật nhân viên: " + e.getMessage());
            }
        }
    }

    public static void searchEmployee() {
        Repository<Employee, String> employeeRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào tên nhân viên cần tìm kiếm:");
        String empName = Console.scanner.nextLine();
        Employee employee = employeeRepository.findName(empName, Employee.class);
        if (employee != null) {
            System.out.format("%-15s%-30s%-15s%-35s%-15s%-35s%-15s\n",
                    "Mã nhân viên", "Tên nhân viên", "Ngày sinh",
                    "Email", "Số điện thoại", "Địa chỉ", "Trạng thái");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.format("%-15s%-30s%-15s%-35s%-15s%-35s%-15s\n",
                    employee.getEmpId(), employee.getEmpName(),
                    dateFormat.format(employee.getBirthOfDate()), employee.getEmail(),
                    employee.getPhone(), employee.getAddress(),
                    employeeService.getStatusString(employee.getEmpStatus()));
        } else {
            System.out.println("Nhân viên không tồn tại!");
        }
    }

    public static void updateEmployeeStatus() {
        Repository<Employee, String> employeeRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã nhân viên cần cập nhật trạng thái:");
        String empId = Console.scanner.nextLine();
        Employee employeeToUpdate = employeeRepository.findId(empId, Employee.class);

        if (employeeToUpdate == null) {
            System.out.println("Không tìm thấy nhân viên!");
        } else {
            try {
                System.out.println("Chọn trạng thái mới cho nhân viên:");
                System.out.println("0: Hoạt động");
                System.out.println("1: Nghỉ chế độ");
                System.out.println("2: Nghỉ việc");
                int newStatus = Integer.parseInt(Console.scanner.nextLine());

                switch (newStatus) {
                    case 0:
                        employeeToUpdate.setEmpStatus(ContStatus.EmpStt.ACTIVE);
                        break;
                    case 1:
                        employeeToUpdate.setEmpStatus(ContStatus.EmpStt.SLEEP);
                        break;
                    case 2:
                        employeeToUpdate.setEmpStatus(ContStatus.EmpStt.QUIT);
                        break;
                    default:
                        System.out.println("Trạng thái không hợp lệ!");
                        return;
                }

                employeeRepository.edit(employeeToUpdate);
                System.out.println("Cập nhật trạng thái nhân viên thành công!");
            } catch (NumberFormatException e) {
                System.out.println("Trạng thái không hợp lệ!");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi cập nhật trạng thái nhân viên!");
                e.printStackTrace();
            }
        }
    }


}
