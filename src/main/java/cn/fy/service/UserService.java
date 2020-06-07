package cn.fy.service;

import cn.fy.domain.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserService {

    public boolean addUser(User user);

    /**
     * 用户注册的service
     * @param user
     * @return
     */
    public boolean regist(User user);

    public User findByUsername(String username);


}
