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
}
