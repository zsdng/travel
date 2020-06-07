package cn.fy.service.impl;

import cn.fy.dao.UserDao;
import cn.fy.domain.User;
import cn.fy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {

      return   userDao.addUser(user);
    }

    @Override
    public boolean regist(User user) {
        //1.根据用户名查询对象
        User user1 = userDao.findByUsername(user.getUsername());
        if (user1!=null){
            //用户名存在注册失败
            return false;
        }
        //2.保存用户信息
        userDao.addUser(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {

        return  userDao.findByUsername(username);
    }
}
