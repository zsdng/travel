package cn.fy.service.impl;

import cn.fy.dao.UserDao;
import cn.fy.domain.User;
import cn.fy.service.UserService;
import cn.fy.utils.MailUtils;
import cn.fy.utils.UuidUtil;
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
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");
        userDao.addUser(user);
        //3.激活右键发送，右键正文
        String content="<a href='http://localhost:8080/travel/user/activeUser?code="+user.getCode()+"'>点击激活[fy旅游网]</a>";
        MailUtils.sendMail(user.getEmail(),content,"用户"+user.getName()+"请您激活您的账户");

        return true;
    }

    @Override
    public User findByUsername(String username) {

        return  userDao.findByUsername(username);
    }

    @Override
    public boolean active(String code) {
        User byCode = userDao.findByCode(code);
        if (byCode!=null){
            userDao.updateStatus(byCode);
            return true;
        }
        return false;
    }
}
