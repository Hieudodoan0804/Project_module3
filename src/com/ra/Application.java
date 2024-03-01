package com.ra;
import com.ra.management.*;
import com.ra.service.admin.AccountService;
import com.ra.service.admin.impl.AccountServiceImpl;
import com.ra.service.user.UserService;
import com.ra.util.Console;

public class Application {
    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
            System.out.print("Username: ");
            String username = Console.scanner.nextLine();
            System.out.print("Password: ");
            String password = Console.scanner.nextLine();
            String role = accountService.authenticate(username,password) ;
            if (role.equals("admin")) {
                adminMenu();
            } else if (role.equals("user")) {
                userMenu();
            } else {
                System.out.println("Đăng nhập không thành công. Vui lòng thử lại.");
            }
    }
    private static void adminMenu() {
        boolean isExist = true;
        do {
            System.out.println("******************WAREHOUSE MANAGEMENT ADMIN****************");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý phiếu nhập");
            System.out.println("5. Quản lý phiếu xuất");
            System.out.println("6. Quản lý báo cáo");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductManagement.productManagement(Console.scanner);
                    break;
                case 2:
                    EmployeeManagement.employeeManagement(Console.scanner);
                    break;
                case 3:
                    AccountManagement.accountManagement(Console.scanner);
                    break;
                case 4:
                    ReceiptManagement.receiptManagement(Console.scanner);
                    break;
                case 5:
                    BillManagement.billManagement(Console.scanner);
                    break;
                case 6:
                    ReportManagement.reportManagement(Console.scanner);
                    break;
                case 7:
                    isExist = false;
                    System.out.println("Ứng dụng đã thoát.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }while (isExist);
    }

    private static void userMenu() {
        boolean isExist = true;
        do {
            System.out.println("******************WAREHOUSE MANAGEMENT USER****************");
            System.out.println("1. Danh sách phiếu nhập theo trạng thái");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật phiếu nhập");
            System.out.println("4. Tìm kiếm phiếu nhập");
            System.out.println("5. Danh sách phiếu xuất theo trạng thái");
            System.out.println("6. Tạo phiếu xuất");
            System.out.println("7. Cập nhật phiếu xuất");
            System.out.println("8. Tìm kiếm phiếu xuất");
            System.out.println("9. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    UserService.viewReceiptsByStatus();
                    break;
                case 2:
                    UserService.createReceipt();
                    break;
                case 3:
                    UserService.updateReceipt();
                    break;
                case 4:
                    UserService.searchReceipt();
                    break;
                case 5:
                    UserService.viewBillsByStatus();
                    break;
                case 6:
                    UserService.createBill();
                    break;
                case 7:
                    UserService.updateBill();
                    break;
                case 8:
                    UserService.searchBill();
                    break;
                case 9:
                    isExist = false;
                    System.out.println("Ứng dụng đã thoát.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }while (isExist);
    }
}
