package com.ra.entity;

import com.ra.util.Column;
import com.ra.util.Id;
import com.ra.util.Table;
@Table(name = "BILL_DETAIL")
public class BillDetail {
    @Id
    @Column(name = "Bill_Detail_Id")
    private int billDetailId;
    @Column(name = "Bill_Id")
    private int billId;
    @Column(name = "Product_Id")
    private String productId;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Price")
    private float price;

    public BillDetail() {
    }

    public BillDetail(int billDetailId, int billId, String productId, int quantity, float price) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void displayBillDetail(){
        System.out.printf("| %-20s | %-10s | %-15s | %-10s | %-15s |\n",
           this.billDetailId,this.billId,this.productId,this.quantity,this.price );

    }
}
