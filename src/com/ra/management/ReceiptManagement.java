package com.ra.management;

import com.ra.util.Console;

import java.util.Scanner;

public class ReceiptManagement {
    public static void receiptManagement(Scanner scanner){
        boolean isExist = true;
        do {
            System.out.println("******************RECEIPT MANAGEMENT****************");
            System.out.println("1.Danh sách phiếu nhập");
            System.out.println("2.Tạo phiếu nhập");
            System.out.println("3.Cập nhật thông tin phiếu nhập");
            System.out.println("4.Chi tiết phiếu nhập");
            System.out.println("5.Duyệt phiếu nhập");
            System.out.println("6.Tìm kiếm phiếu nhập");
            System.out.println("7.Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:

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
}
