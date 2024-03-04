package com.ra.management;

import com.ra.entity.Account;
import com.ra.model.ContStatus;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.util.Console;

import java.util.List;
import java.util.Scanner;

public class AccountManagement {
    public static void accountManagement() {
        boolean isExist = true;
        do {
            System.out.println("******************ACCOUNT MANAGEMENT****************");
            System.out.println("1.Danh sách tài khoản");
            System.out.println("2.Tạo tài khoản mới");
            System.out.println("3.Cập nhật trạng thái tài khoản");
            System.out.println("4.Tìm kiếm tài khoản");
            System.out.println("5.Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    displayAccountList();
                    break;
                case 2:
                    addAccount();
                    break;
                case 3:
                    updateAccountStatus();
                    break;
                case 4:
                    searchAccount();
                    break;
                case 5:
                    isExist = false;
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (isExist);
    }

    public static void displayAccountList() {
        Repository<Account, String> accRepository = new RepositoryImpl<>();
        List<Account> accountList = accRepository.findAll(Account.class);
        if (!accountList.isEmpty()) {
            System.out.println("Danh sách tài khoản:");
            System.out.format("%-15s%-30s%-20s%-20s%-15s%-15s\n",
                    "Mã tài khoản", "Tên tài khoản", "Mật khẩu",
                    "Quyền tài khoản", "Mã nhân viên", "Trạng thái");
            for (Account account : accountList) {
                System.out.format("%-15s%-30s%-20s%-20s%-15s%-15s\n",
                        account.getAccId(), account.getUserName(),
                        account.getPassword(), account.isPermission() ? "user" : "admin",
                        account.getEmpId(), account.isAccStatus() ? "Active" : "Block");
            }
        } else {
            System.out.println("Không có tài khoản nào trong danh sách.");
        }
    }

    public static void addAccount() {
        Repository<Account, String> accRepository = new RepositoryImpl<>();
            Account account = new Account();
            System.out.println("Nhập tên tài khoản:");
            String userName = Console.scanner.nextLine();
            account.setUserName(userName);
            System.out.println("Nhập mật khẩu:");
            String password = Console.scanner.nextLine();
            account.setPassword(password);
            System.out.println("Nhập quyền tài khoản:");
            boolean permission = Boolean.parseBoolean(Console.scanner.nextLine());
            account.setPermission(permission);
            System.out.println("Nhập mã nhân viên:");
            String empId = Console.scanner.nextLine();
            account.setEmpId(empId);
            System.out.println("Nhập trạng thái");
            boolean status = Boolean.parseBoolean(Console.scanner.nextLine());
            account.setAccStatus(status);
            accRepository.add(account);
            System.out.println("Thêm mới tài khoản thành công!");
    }

    public static void searchAccount() {
        Repository<Account, String> accRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào tên tài khoản cần tìm kiếm:");
        String userName = Console.scanner.nextLine();
        Account account = accRepository.findName(userName, Account.class);
        if (account != null) {
            System.out.format("%-15s%-30s%-20s%-20s%-15s%-15s\n",
                    "Mã tài khoản", "Tên tài khoản", "Mật khẩu",
                    "Quyền tài khoản", "Mã nhân viên", "Trạng thái");
            System.out.format("%-15s%-30s%-20s%-20s%-15s%-15s\n",
                    account.getAccId(), account.getUserName(),
                    account.getPassword(), account.isPermission() ? "user" : "admin",
                    account.getEmpId(), account.isAccStatus() ? "Active" : "Block");
            System.out.print("Bạn muốn cập nhật trạng thái tài khoản không (Y/N)?: ");
            String updateChoice = Console.scanner.nextLine();
            if (updateChoice.equalsIgnoreCase("Y")) {
                System.out.println("Chọn trạng thái mới cho tài khoản:");
                System.out.println("0: Block");
                System.out.println("1: Active");
                int newStatus = Integer.parseInt(Console.scanner.nextLine());

                switch (newStatus) {
                    case 0:
                        account.setAccStatus(ContStatus.AccountStt.BLOCK);
                        break;
                    case 1:
                        account.setAccStatus(ContStatus.AccountStt.ACTIVE);
                        break;
                    default:
                        System.out.println("Trạng thái không hợp lệ!");
                        return;
                }
                accRepository.edit(account);
                System.out.println("Cập nhật trạng thái tài khoản thành công!");
            } else {
                System.out.println("Quay lại menu chính. ");
            }
        } else {
            System.out.println("Tài khoản không tồn tại!");
        }
    }

    public static void updateAccountStatus() {
        Repository<Account, String> accRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã tài khoản cần cập nhật trạng thái:");
        String accId = Console.scanner.nextLine();
        Account account = accRepository.findId(accId, Account.class);

        if (account == null) {
            System.out.println("Không tìm thấy tài khoản!");
        } else {
            try {
                System.out.println("Chọn trạng thái mới cho tài khoản:");
                System.out.println("0: Block");
                System.out.println("1: Active");
                int newStatus = Integer.parseInt(Console.scanner.nextLine());

                switch (newStatus) {
                    case 0:
                        account.setAccStatus(ContStatus.AccountStt.BLOCK);
                        break;
                    case 1:
                        account.setAccStatus(ContStatus.AccountStt.ACTIVE);
                        break;
                    default:
                        System.out.println("Trạng thái không hợp lệ!");
                        return;
                }
                accRepository.edit(account);
                System.out.println("Cập nhật trạng thái tài khoản thành công!");
            } catch (NumberFormatException e) {
                System.out.println("Trạng thái không hợp lệ!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Lỗi khi cập nhật trạng thái tài khoản!");
            }
        }
    }
}