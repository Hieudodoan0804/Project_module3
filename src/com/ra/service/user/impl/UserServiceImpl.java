package com.ra.service.user.impl;

import com.ra.entity.Bill;
import com.ra.entity.BillDetail;
import com.ra.entity.Product;
import com.ra.model.BillType;
import com.ra.model.ContStatus;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.impl.BillServiceImpl;
import com.ra.service.user.UserService;
import com.ra.util.Console;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    static BillServiceImpl billService = new BillServiceImpl();


    @Override
    public void viewReceiptsByStatus() {
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        List<Bill> billLists = billRepository.findAll(Bill.class);

        if (billLists.isEmpty()) {
            System.out.println("Danh sách phiếu xuất trống:");

        } else {
            System.out.println("Chọn trạng thái muốn xem (0: Tạo, 1: Huỷ, 2: Duyệt): ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 0:
                    System.out.println("Danh sách phiếu xuất theo trạng thái " + ContStatus.BillStt.CREATE + ":");
                    System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                    for (Bill bill : billLists) {
                        if (bill.isBillType() == BillType.IMPORT && bill.getBillStatus() == ContStatus.BillStt.CREATE) {
                            System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                                    bill.getBillId(), bill.getBillCode(), bill.isBillType() ? "Phiếu nhập" : "Phiếu xuất", bill.getCreated(),
                                    bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                        }
                    }
                    break;
                case 1:
                    System.out.println("Danh sách phiếu xuất theo trạng thái " + ContStatus.BillStt.CANCEL + ":");
                    System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                    for (Bill bill : billLists) {
                        if (bill.isBillType() == BillType.IMPORT && bill.getBillStatus() == ContStatus.BillStt.CANCEL) {
                            System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                                    bill.getBillId(), bill.getBillCode(), bill.isBillType() ? "Phiếu nhập" : "Phiếu xuất", bill.getCreated(),
                                    bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Danh sách phiếu xuất theo trạng thái " + ContStatus.BillStt.APPROVE + ":");
                    System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                    for (Bill bill : billLists) {
                        if (bill.isBillType() == BillType.IMPORT && bill.getBillStatus() == ContStatus.BillStt.APPROVE) {
                            System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                                    bill.getBillId(), bill.getBillCode(), bill.isBillType() ? "Phiếu nhập" : "Phiếu xuất", bill.getCreated(),
                                    bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                        }
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    @Override
    public void createReceipt() {

    }

    @Override
    public void updateReceipt() {

    }

    @Override
    public void searchReceipt() {

    }

    @Override
    public void viewBillsByStatus() {
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        List<Bill> billLists = billRepository.findAll(Bill.class);

        if (billLists.isEmpty()) {
            System.out.println("Danh sách phiếu xuất trống:");

        } else {
            System.out.println("Chọn trạng thái muốn xem (0: Tạo, 1: Huỷ, 2: Duyệt): ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 0:
                    System.out.println("Danh sách phiếu xuất theo trạng thái " + ContStatus.BillStt.CREATE + ":");
                    System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                    for (Bill bill : billLists) {
                        if (bill.isBillType() == BillType.EXPORT && bill.getBillStatus() == ContStatus.BillStt.CREATE) {
                            System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                                    bill.getBillId(), bill.getBillCode(), bill.isBillType() ? "Phiếu nhập" : "Phiếu xuất", bill.getCreated(),
                                    bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                        }
                    }
                    break;
                case 1:
                    System.out.println("Danh sách phiếu xuất theo trạng thái " + ContStatus.BillStt.CANCEL + ":");
                    System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                    for (Bill bill : billLists) {
                        if (bill.isBillType() == BillType.EXPORT && bill.getBillStatus() == ContStatus.BillStt.CANCEL) {
                            System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                                    bill.getBillId(), bill.getBillCode(), bill.isBillType() ? "Phiếu nhập" : "Phiếu xuất", bill.getCreated(),
                                    bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Danh sách phiếu xuất theo trạng thái " + ContStatus.BillStt.APPROVE + ":");
                    System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                    for (Bill bill : billLists) {
                        if (bill.isBillType() == BillType.EXPORT && bill.getBillStatus() == ContStatus.BillStt.APPROVE) {
                            System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                                    bill.getBillId(), bill.getBillCode(), bill.isBillType() ? "Phiếu nhập" : "Phiếu xuất", bill.getCreated(),
                                    bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                        }
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    @Override
    public void createBill() {
        Repository<Product, String> productRepository = new RepositoryImpl<>();
        Repository<BillDetail, String> billDetailRepository = new RepositoryImpl();
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

        boolean stop = false;
        while (!stop) {
            System.out.print("Nhập mã sản phẩm: ");
            String productId = Console.scanner.nextLine();
            Product product = productRepository.findId(productId, Product.class);
            if (product != null && product.isProductStatus() == ContStatus.ProductStt.ACTIVE) {
                System.out.println("Nhập số lượng sản phẩm xuất:");
                int quantityOfProExported = Integer.parseInt(Console.scanner.nextLine());
                System.out.println("Nhập giá sản phẩm xuất:");
                float price = Float.parseFloat(Console.scanner.nextLine());

                if (quantityOfProExported <= product.getQuantity()) {
                    BillDetail billDetail = new BillDetail();
                    billDetail.setBillId(billId);
                    billDetail.setProductId(productId);
                    billDetail.setQuantity(quantityOfProExported);
                    billDetail.setPrice(price);
                    billDetailRepository.add(billDetail);
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

    @Override
    public void updateBill() {
        Repository<BillDetail, Integer> billDetaiRepository = new RepositoryImpl<>();
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã phiếu cần cập nhật thông tin:");
        int billId = Integer.parseInt(Console.scanner.nextLine());
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill == null) {
            System.out.println("Không tìm thấy phiếu!");
        } else {
            if (bill.getEmpIdCreated().equals(System.getProperty("user.id"))){
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
            }else {
                System.out.println("Bạn không có quyền cập nhật phiếu này.");
            }

        }
    }

    @Override
    public void searchBill() {
        Repository<Bill, Integer> billRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã phiếu cần tìm kiếm thông tin:");
        int billId = Integer.parseInt(Console.scanner.nextLine());
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill == null) {
            System.out.println("Không tìm thấy phiếu!");
        } else {
            if (bill.getEmpIdCreated().equals(System.getProperty("user.id"))){
                System.out.printf("| %-10s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                        "Mã phiếu", "Mã code", "Loại", "Ngày tạo", "Người tạo", "Trạng thái", "Người duyệt");
                if (bill.isBillType() == BillType.EXPORT) {
                    System.out.printf("| %-10d | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s |\n",
                            bill.getBillId(), bill.getBillCode(), bill.isBillType()?"Phiếu nhập":"Phiếu xuất", bill.getCreated(),
                            bill.getEmpIdCreated(), billService.getBillStatusAsString(bill.getBillStatus()), bill.getEmpIdAuth());
                }
            }else {
                System.out.println("Bạn không có quyền cập nhật phiếu này.");
            }

        }
    }
}
