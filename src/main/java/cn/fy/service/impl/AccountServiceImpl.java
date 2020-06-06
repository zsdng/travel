package cn.fy.service.impl;

import cn.fy.dao.AccountDao;
import cn.fy.domain.Account;
import cn.fy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        System.out.println("业务层findAll方法");
        return accountDao.findAll();
    }

    @Override
    public void addAccount(Account account) {
        System.out.println("业务层add方法");
        accountDao.addAccount(account);
    }
}
