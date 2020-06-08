package cn.fy.dao;

import cn.fy.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {


    public boolean addUser(User user);

    /**
     * 根据用户名查询用户是否存在来确保用户名唯一
     * @param username
     * @return
     */
    public  User findByUsername(String username);

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    public User findByCode(String code);

    /**
     * 修改激活状态
     * @param user
     */
    public void updateStatus(User user);

    /**
     * 登录的时候判断用户是否存在
     */
    public void findByUsernameAndPassword(User user);

}
