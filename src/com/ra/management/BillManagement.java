package com.ra.management;

import com.ra.entity.Bill;
import com.ra.entity.Employee;
import com.ra.model.BillType;
import com.ra.model.ContStatus;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.impl.BillServiceImpl;
import com.ra.util.Console;
import com.ra.util.Storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BillManagement {
    static BillServiceImpl billService = new BillServiceImpl();
    public static void billManagement(Scanner scanner){
        boolean isExist = true;
        do {
            System.out.println("******************BILL MANAGEMENT****************");
            System.out.println("1.Danh sách phiếu xuất");
            System.out.println("2.Tạo phiếu xuất");
            System.out.println("3.Cập nhật thông tin phiếu xuất");
            System.out.println("4.Chi tiết phiếu xuất");
            System.out.println("5.Duyệt phiếu xuất");
            System.out.println("6.Tìm kiếm phiếu xuất");
            System.out.println("7.Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    displayBillList();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    isExist = false;
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (isExist);
    }

    public static void displayBillList() {
        Repository<Bill,Long> billRepository = new RepositoryImpl<>();
        List<Bill> billLists = billRepository.findAll(Bill.class);

        if (billLists.isEmpty()) {
            System.out.println("Danh sách phiếu xuất trống:");

        } else {
            System.out.println("Danh sách phiếu xuất:");
            System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                    "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
            for (Bill bill : billLists) {
                System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                        bill.getBillId(), bill.getBillCode(),bill.isBillType(), bill.getCreated(),
                        bill.getEmpIdCreated(),billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
            }
        }
    }
    public static void addBill(){
        Repository<Bill,String> billRepository = new RepositoryImpl<>();
        System.out.println("Nhập số phiếu xuất cần nhập thông tin:");
        int numberOfBill = Integer.parseInt(Console.scanner.nextLine());
        for (int i = 0; i < numberOfBill; i++) {
            Bill bill = new Bill();
            bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
            bill.setBillType(BillType.EXPORT);
            bill.setEmpIdCreated(Storage.current_user);
            bill.setCreated(new Date());
            System.out.println("Nhập mã nhân viên duyệt:");
            String empIdAuth = Console.scanner.nextLine();
            bill.setEmpIdAuth(empIdAuth);
            bill.setBillStatus(ContStatus.BillStt.CREATE);
            billRepository.add(bill);
            System.out.println("Thêm mới nhân viên thành công!");
        }
    }
}
