package com.ra.management;

import com.ra.service.admin.impl.ReportServiceImpl;
import com.ra.util.Console;

public class ReportManagement {
    static ReportServiceImpl reportService = new ReportServiceImpl();
    public static void reportManagement(){
        boolean isExist = true;
        do {
            System.out.println("******************REPORT MANAGEMENT****************");
            System.out.println("1.Thống kê chi phí theo ngày, tháng, năm");
            System.out.println("2.Thống kê chi phí theo khoảng thời gian");
            System.out.println("3.Thống kê doanh thu theo ngày, tháng, năm");
            System.out.println("4.Thống kê doanh thu theo khoảng thời gian");
            System.out.println("5.Thống kê số nhân viên theo từng trạng thái");
            System.out.println("6.Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian");
            System.out.println("7.Thống kê sản phẩm nhập ít nhất trong khoảng thời gian");
            System.out.println("8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian");
            System.out.println("9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian");
            System.out.println("10.Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    reportService.CostByDate();
                    break;
                case 2:
                    System.out.println("Nhập vào ngày đầu:(yyyy-mm-dd)");
                    String startDate1 = Console.scanner.nextLine();
                    System.out.println("Nhập vào ngày cuối:(yyyy-mm-dd)");
                    String endDate1 = Console.scanner.nextLine();
                    reportService.CostByTimeRange(startDate1,endDate1);
                    break;
                case 3:
                    reportService.RevenueByDate();
                    break;
                case 4:
                    System.out.println("Nhập vào ngày đầu:(yyyy-mm-dd)");
                    String startDate2 = Console.scanner.nextLine();
                    System.out.println("Nhập vào ngày cuối:(yyyy-mm-dd)");
                    String endDate2 = Console.scanner.nextLine();
                    reportService.RevenueByTimeRange(startDate2,endDate2);
                    break;
                case 5:
                    reportService.EmployeeStatusCount();
                    break;
                case 6:
                    System.out.println("Nhập vào ngày đầu:(yyyy-mm-dd)");
                    String startDate3 = Console.scanner.nextLine();
                    System.out.println("Nhập vào ngày cuối:(yyyy-mm-dd)");
                    String endDate3 = Console.scanner.nextLine();
                    reportService.MostImportedProduct(startDate3,endDate3);
                    break;
                case 7:
                    System.out.println("Nhập vào ngày đầu:(yyyy-mm-dd)");
                    String startDate4 = Console.scanner.nextLine();
                    System.out.println("Nhập vào ngày cuối:(yyyy-mm-dd)");
                    String endDate4 = Console.scanner.nextLine();
                    reportService.LeastImportedProduct(startDate4,endDate4);
                    break;
                case 8:
                    System.out.println("Nhập vào ngày đầu:(yyyy-mm-dd)");
                    String startDate5 = Console.scanner.nextLine();
                    System.out.println("Nhập vào ngày cuối:(yyyy-mm-dd)");
                    String endDate5 = Console.scanner.nextLine();
                    reportService.MostExportedProduct(startDate5,endDate5);
                    break;
                case 9:
                    System.out.println("Nhập vào ngày đầu:(yyyy-mm-dd)");
                    String startDate6 = Console.scanner.nextLine();
                    System.out.println("Nhập vào ngày cuối:(yyyy-mm-dd)");
                    String endDate6 = Console.scanner.nextLine();
                    reportService.LeastExportedProduct(startDate6,endDate6);
                    break;
                case 10:
                    isExist = false;
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (isExist);
    }
}
