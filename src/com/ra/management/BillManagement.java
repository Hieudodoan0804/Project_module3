package com.ra.management;

import com.ra.entity.Bill;
import com.ra.entity.BillDetail;
import com.ra.entity.Employee;
import com.ra.entity.Product;
import com.ra.model.BillType;
import com.ra.model.ContStatus;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.impl.BillServiceImpl;
import com.ra.util.Console;
import com.ra.util.Storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BillManagement {
    static BillServiceImpl billService = new BillServiceImpl();

    public static void billManagement() {
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
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    displayBillExportList();
                    break;
                case 2:
                    addBillExport();
                    break;
                case 3:
                    updateBillExport();
                    break;
                case 4:
                    listBillDetailExport();
                    break;
                case 5:
                    approveBillExport();
                    break;
                case 6:
                    searchBillExport();
                    break;
                case 7:
                    isExist = false;
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (isExist);
    }

    public static void displayBillExportList() {
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        List<Bill> billLists = billRepository.findAll(Bill.class);

        if (billLists.isEmpty()) {
            System.out.println("Danh sách phiếu xuất trống:");

        } else {
            System.out.println("Danh sách phiếu xuất:");
            System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                    "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
            for (Bill bill : billLists) {
                if (bill.isBillType() == BillType.EXPORT) {
                    System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            bill.getBillId(), bill.getBillCode(), bill.isBillType()?"Phiếu nhập":"Phiếu xuất", bill.getCreated(),
                            bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                }

            }
        }
    }

    public static void addBillExport() {
        Repository<Product, String> productRepository = new RepositoryImpl<>();
        Repository<BillDetail, String> billDetaiRepository = new RepositoryImpl<>();
        boolean stop = false;
        Bill bill = new Bill();
        bill.setBillCode(("B" + System.currentTimeMillis()).substring(0, 9));
        bill.setBillType(BillType.EXPORT);
        bill.setEmpIdCreated(System.getProperty("user.id"));
        bill.setCreated(new Date());
        System.out.println("Nhập mã nhân viên duyệt:");
        String empIdAuth = Console.scanner.nextLine();
        bill.setEmpIdAuth(empIdAuth);
        bill.setAuthDate(new Date());
        bill.setBillStatus(ContStatus.BillStt.CREATE);
        int billId = billService.add(bill);
        System.out.println("Thêm mới phiếu xuất thành công!");
        System.out.println("Mã code: " + bill.getBillCode());
        while (!stop) {
            System.out.print("Nhập mã sản phẩm: ");
            String prodId = Console.scanner.nextLine();
            Product product = productRepository.findId(prodId, Product.class);
            if (product != null && product.isProductStatus() == ContStatus.ProductStt.ACTIVE) {
                // Nhập số lượng xuất
                System.out.println("Nhập số lượng sản phẩm xuất:");
                int quantityOfProExported = Integer.parseInt(Console.scanner.nextLine());
                System.out.println("Nhập gía sản phẩm xuất:");
                float price = Float.parseFloat(Console.scanner.nextLine());
                // Kiểm tra số lượng còn trong kho
                BillDetail billDetail = new BillDetail();
                if (quantityOfProExported <= product.getQuantity()) {
                    billDetail.setBillId(billId);
                    billDetail.setProductId(prodId);
                    billDetail.setQuantity(quantityOfProExported);
                    billDetail.setPrice(price);
                    billDetaiRepository.add(billDetail);
                } else {
                    System.out.println("Số lượng sản phẩm không đủ trong kho!");
                }
                System.out.print("Bạn có muốn tiếp tục nhập không (Y/N)?: ");
                String ans = Console.scanner.nextLine();
                if (!ans.equals("Y")) {
                    stop = true;
                }
            } else {
                System.out.println("Sản phẩm không tồn tại hoặc đã ngừng kinh doanh!");
                System.out.print("Bạn có muốn tiếp tục nhập không (Y/N)?: ");
                String ans = Console.scanner.nextLine();
                if (!ans.equals("Y")) {
                    stop = true;
                }
            }
        }

    }

    public static void updateBillExport() {
        Repository<BillDetail, Integer> billDetaiRepository = new RepositoryImpl<>();
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã phiếu cần cập nhật thông tin:");
        int billId = Integer.parseInt(Console.scanner.nextLine());
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill == null) {
            System.out.println("Không tìm thấy phiếu!");
        } else {
            try {
                if (bill.getBillStatus() == ContStatus.BillStt.CREATE || bill.getBillStatus() == ContStatus.BillStt.CANCEL) {
                    System.out.println("Nhập mã nhân viên duyệt:");
                    String empIdAuth = Console.scanner.nextLine();
                    bill.setEmpIdAuth(empIdAuth);
                    bill.setAuthDate(new Date());
                    System.out.print("Bạn muốn cập nhật chi tiết phiếu xuất không (Y/N)?: ");
                    String updateChoice = Console.scanner.nextLine();
                    if (updateChoice.equalsIgnoreCase("Y")){
                        List<BillDetail> billDetailList = billService.getBillDetailByBillCode(bill.getBillCode());
                        if (billDetailList != null && !billDetailList.isEmpty()) {
                            System.out.printf("| %-10s | %-10s | %-15s | %-10s | %-15s |\n",
                                    "Mã phiếu chi tiết","Mã phiếu","Mã sản phẩm", "Số lượng", "Giá xuất");
                            billDetailList.forEach(BillDetail::displayBillDetail);
                            System.out.println("Nhập mã phiếu chi tiết:");
                            int billDetailId = Integer.parseInt(Console.scanner.nextLine());
                            BillDetail billDetail = billDetaiRepository.findId(billDetailId, BillDetail.class);
                            if (billDetail != null){
                                System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                                String productIdToUpdate = Console.scanner.nextLine();
                                billDetail.setProductId(productIdToUpdate);
                                System.out.print("Nhập số lượng mới: ");
                                int newQuantity = Integer.parseInt(Console.scanner.nextLine());
                                billDetail.setQuantity(newQuantity);
                                System.out.print("Nhập giá mới: ");
                                float newPrice = Float.parseFloat(Console.scanner.nextLine());
                                billDetail.setPrice(newPrice);
                                billDetaiRepository.edit(billDetail);
                                System.out.println("Cập nhật chi tiết phiếu thành công!");
                            }else {
                                System.out.println("Không tìm thấy mã phiếu chi tiết!");
                            }

                        } else {
                            System.out.println("Không tìm thấy chi tiết phiếu!");
                        }
                    }else {
                        System.out.println("Quay lại menu chính. ");
                    }
                    billRepository.edit(bill);
                    System.out.println("Cập nhật phiếu xuất thành công!");
                }else {
                    System.out.println("Không thể cập nhật phiếu do phiếu đã duyệt!");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi cập nhật phiếu xuất: " + e.getMessage());
            }
        }
    }

    public static void searchBillExport() {
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã phiếu cần tìm kiếm thông tin:");
        int billId = Integer.parseInt(Console.scanner.nextLine());
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill == null) {
            System.out.println("Không tìm thấy phiếu!");
        } else {
            System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                    "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
            if (bill.isBillType() == BillType.EXPORT) {
                System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                        bill.getBillId(), bill.getBillCode(), bill.isBillType()?"Phiếu nhập":"Phiếu xuất", bill.getCreated(),
                        bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
            }
        }
    }

    public static void approveBillExport() {
        Repository<Product, String> productRepository = new RepositoryImpl<>();
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã phiếu cần duyệt:");
        int billId = Integer.parseInt(Console.scanner.nextLine());
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill == null) {
            System.out.println("Không tìm thấy phiếu!");
        }else {
            try {
                if (bill.getBillStatus() == ContStatus.BillStt.CREATE) {
                    bill.setBillStatus(ContStatus.BillStt.APPROVE);
                    billRepository.edit(bill);
                    System.out.println("Duyệt phiếu nhập thành công!");

                    List<BillDetail> billDetails = billService.getBillDetailByBillCode(bill.getBillCode());
                    for (BillDetail billDetail : billDetails) {
                        String productId = billDetail.getProductId();
                        int quantity = billDetail.getQuantity();

                        Product product = productRepository.findId(productId, Product.class);
                        if (product != null) {
                            int currentQuantity = product.getQuantity();
                            product.setQuantity(currentQuantity - quantity);
                            productRepository.edit(product);
                        }
                    }

                    System.out.println("Đã cập nhật số lượng sản phẩm trong kho!");
                } else {
                    System.out.println("Không thể duyệt phiếu đã duyệt hoặc đã hủy!");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi duyệt phiếu: " + e.getMessage());
            }
        }
    }

    public static void listBillDetailExport() {
        System.out.println("Nhập vào mã code");
        String billCode = Console.scanner.nextLine();
        List<BillDetail> billDetailList = billService.getBillDetailByBillCode(billCode);
        if (billDetailList == null) {
            System.out.println("Không tìm thấy phiếu!");
        } else {
            System.out.println("Chi tiết phiếu xuất:");
            System.out.printf("| %-20s | %-10s | %-15s | %-10s | %-15s |\n",
                    "Mã phiếu chi tiết","Mã phiếu","Mã sản phẩm", "Số lượng", "Giá xuất");
            for (BillDetail billDetail : billDetailList) {
               billDetail.displayBillDetail();
            }
        }
    }
}
