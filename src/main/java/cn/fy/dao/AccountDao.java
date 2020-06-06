package cn.fy.dao;

import cn.fy.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Fy
 *账户dao接口
 * @create: 2020-04-16 10:11
 */
@Mapper
public interface AccountDao {

    //查询所有账户
//    @Select("select * from account")
    public List<Account> findAll();

    //添加账户
//    @Insert("insert into account(name,money) values (#{name}, #{money})")
    public void addAccount(Account account);

}
