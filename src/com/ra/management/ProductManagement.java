package com.ra.management;

import com.ra.entity.Product;
import com.ra.model.ContStatus;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.impl.ProductServiceImpl;
import com.ra.util.Console;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    static ProductServiceImpl productService = new ProductServiceImpl();

    public static void productManagement(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("******************PRODUCT MANAGEMENT****************");
            System.out.println("1.Danh sách sản phẩm");
            System.out.println("2.Thêm mới sản phẩm");
            System.out.println("3.Cập nhật sản phẩm");
            System.out.println("4.Tìm kiếm sản phẩm");
            System.out.println("5.Cập nhật trạng thái sản phẩm");
            System.out.println("6.Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    displayProductList();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    searchProduct();
                    break;
                case 5:
                    updateProductStatus();
                    break;
                case 6:
                    isExist = false;
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (isExist);
    }
    public static void displayProductList(){
        Repository<Product,String> productRepository = new RepositoryImpl<>();
        List<Product> productList = productRepository.findAll(Product.class);

        if (!productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm:");
            System.out.format("%-15s%-30s%-20s%-20s%-15s%-10s%-15s\n",
                    "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất",
                    "Ngày tạo", "Lô", "Số lượng", "Trạng thái");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (Product product : productList) {
                System.out.format("%-15s%-30s%-20s%-20s%-15d%-10d%-15s\n",
                        product.getProductId(), product.getProductName(),
                        product.getManufacturer(), dateFormat.format(product.getCreated()),
                        product.getBatch(), product.getQuantity(),
                        product.isProductStatus() ? "Hoạt động" : "Không hoạt động");
            }
        } else {
            System.out.println("Không có sản phẩm nào trong danh sách.");
        }
    }
    public static void addProduct(){
        Repository<Product,String> productRepository = new RepositoryImpl<>();
        System.out.println("Nhập số sản phẩm cần nhập thông tin:");
        int numberOfProducts = Integer.parseInt(Console.scanner.nextLine());
        for (int i = 0; i < numberOfProducts; i++) {
            Product newProduct = new Product();
            System.out.println("Nhập mã sản phẩm:");
            String id = Console.scanner.nextLine();
            newProduct.setProductId(id);
            System.out.println("Nhập tên sản phẩm:");
            String name = Console.scanner.nextLine();
            newProduct.setProductName(name);
            System.out.println("Nhập nhà sản xuất:");
            String manufacturer = Console.scanner.nextLine();
            newProduct.setManufacturer(manufacturer);
            Date currentDate = new Date();
            newProduct.setCreated(currentDate);
            System.out.println("Nhập lô chứa sản phẩm:");
            int batch = Integer.parseInt(Console.scanner.nextLine());
            newProduct.setBatch(batch);
            System.out.println("Nhập số lượng sản phẩm:");
            int quantity = Integer.parseInt(Console.scanner.nextLine());
            newProduct.setQuantity(quantity);
            System.out.println("Nhập trạng thái");
            boolean status = Boolean.parseBoolean(Console.scanner.nextLine());
            newProduct.setProductStatus(status);
            productRepository.add(newProduct);
            System.out.println("Thêm mới sản phẩm thành công!");
        }
    }
    public static void updateProduct(){
        Repository<Product,String> productRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã sản phẩm cần cập nhật thông tin:");
        String productId = Console.scanner.nextLine();
        Product productUpdate = productService.findId(productId);
        if (productUpdate == null){
            System.out.println("Không tìm thấy sản phẩm!");
        }else {
            try {
                System.out.println("Nhập tên sản phẩm:");
                String name = Console.scanner.nextLine();
                productUpdate.setProductName(name);
                System.out.println("Nhập nhà sản xuất:");
                String manufacturer = Console.scanner.nextLine();
                productUpdate.setManufacturer(manufacturer);
                Date currentDate = new Date();
                productUpdate.setCreated(currentDate);
                System.out.println("Nhập lô chứa sản phẩm:");
                int batch = Integer.parseInt(Console.scanner.nextLine());
                productUpdate.setBatch(batch);
                System.out.println("Nhập trạng thái");
                boolean status = Boolean.parseBoolean(Console.scanner.nextLine());
                productUpdate.setProductStatus(status);
                productRepository.edit(productUpdate);
                System.out.println("Cập nhật sản phẩm thành công!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void searchProduct(){
        Repository<Product,String> productRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm:");
        String productName = Console.scanner.nextLine();
        Product product = productRepository.findName(productName, Product.class);
        if (product != null) {
            System.out.format("%-15s%-30s%-20s%-20s%-15s%-10s%-15s\n",
                    "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất",
                    "Ngày tạo", "Lô", "Số lượng", "Trạng thái");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.format("%-15s%-30s%-20s%-20s%-15d%-10d%-15s\n",
                    product.getProductId(), product.getProductName(),
                    product.getManufacturer(), dateFormat.format(product.getCreated()),
                    product.getBatch(), product.getQuantity(),
                    product.isProductStatus() ? "Hoạt động" : "Không hoạt động");
        } else {
            System.out.println("Sản phẩm không tồn tại!");
        }
    }

    public static void updateProductStatus(){
        Repository<Product, String> productRepository = new RepositoryImpl<>();
        System.out.println("Nhập vào mã sản phẩm cần cập nhật trạng thái:");
        String productId = Console.scanner.nextLine();
        Product productToUpdate = productRepository.findId(productId, Product.class);

        if (productToUpdate == null) {
            System.out.println("Không tìm thấy sản phẩm!");
        } else {
            try {
                System.out.println("Chọn trạng thái mới cho tài khoản:");
                System.out.println("0: Hoạt động");
                System.out.println("1: Không hoạt động");
                int newStatus = Integer.parseInt(Console.scanner.nextLine());

                switch (newStatus) {
                    case 0:
                        productToUpdate.setProductStatus(ContStatus.ProductStt.INACTION);
                        break;
                    case 1:
                        productToUpdate.setProductStatus(ContStatus.ProductStt.ACTIVE);
                        break;
                    default:
                        System.out.println("Trạng thái không hợp lệ!");
                        return;
                }
                productRepository.edit(productToUpdate);
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
