package com.ra.service.admin;

import com.ra.entity.Account;
import com.ra.entity.Product;

public interface AccountService {
    Account findId(int accId);
    String findName(String userName);
    Account login(String username,String password);
    String authenticate(String username, String password);
}
