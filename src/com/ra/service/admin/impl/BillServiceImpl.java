package com.ra.service.admin.impl;

import com.ra.entity.Bill;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.BillService;

public class BillServiceImpl implements BillService {
    private Repository<Bill,Long> billRepository = new RepositoryImpl<>();
    @Override
    public Bill findId(Long billId) {
        Bill bill = billRepository.findId(billId, Bill.class);
        if (bill.getBillId() == billId){
            return bill;
        }
        return null;
    }
    public String getBillStatusAsString(int billType) {
        switch (billType) {
            case 0:
                return "Tạo";
            case 1:
                return "Huỷ";
            case 2:
                return "Duyệt";
            default:
                return "Không xác định";
        }
    }
}
