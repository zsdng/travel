package cn.fy.service;

import cn.fy.domain.Account;

import java.util.List;

public interface AccountService {
    //查询所有账户
    public List<Account> findAll();

    //添加账户
    public void addAccount(Account account);
}
