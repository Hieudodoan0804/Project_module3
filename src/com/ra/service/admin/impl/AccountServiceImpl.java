package com.ra.service.admin.impl;

import com.ra.entity.Account;
import com.ra.model.PermissionType;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.AccountService;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account findId(int accId) {
        Repository<Account,Integer> accountRepository = new RepositoryImpl<>();
        Account account = accountRepository.findId(accId,Account.class);
        if (account.getAccId()==accId){
            return account;
        }
        return null;
    }

    @Override
    public String findName(String userName) {
        Repository<Account,String> newAccRepository = new RepositoryImpl<>();
        Account account = newAccRepository.findName(userName, Account.class);
        return account.getUserName();
    }
    @Override
    public  Account login(String username,String password){
        Repository<Account,String> newAccRepository = new RepositoryImpl<>();
        Account account = newAccRepository.findName(username, Account.class);
        if (account == null){
            System.out.println("Tài khoản không tồn tại");
            return null;
        }
        if (!account.getPassword().equals(password)){
            System.out.println("Sai mật khẩu");
            return null;
        }
        return account;
    }
    @Override
    public  String authenticate(String username, String password) {
        AccountServiceImpl accountService = new AccountServiceImpl();
        Account account = accountService.login(username,password);
        if (account != null) {
            if (PermissionType.ADMIN == account.isPermission()) {
                return "admin";
            } else {
                return "user";
            }
        }
        return "Không hợp lệ";
    }

}
