package com.ra.model;

public interface ContStatus {
    interface ProductStt {
        boolean ACTIVE = true;
        boolean INACTION = false;
    }
    interface BillStt {
        byte CREATE = 0;
        byte CANCEL = 1;
        byte APPROVE = 2;
    }
    interface AccountStt {
        boolean ACTIVE = true;
        boolean BLOCK = false;
    }
    interface EmpStt {
        byte ACTIVE = 0;
        byte SLEEP = 1;
        byte QUIT = 2;
    }
}
