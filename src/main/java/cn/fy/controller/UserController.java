package cn.fy.controller;

import cn.fy.domain.ResultInfo;
import cn.fy.domain.User;
import cn.fy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResultInfo resultInfo;

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }


    @RequestMapping("/findUser")
    @ResponseBody
    public User findUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @RequestMapping("/findUserByName")
    @ResponseBody
    public boolean findUserByName(String username) {

        return userService.findByUsername(username) != null;

    }

    @RequestMapping(value = "/activeUser", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String activeUser(String code, HttpServletResponse response) throws IOException {


        boolean flag = userService.active(code);

        String msg = null;
        if (flag) {
            //激活成功
            msg = "激活成功请<a href='../login.html'>登录</a>";
        } else {
            //激活失败
            msg = "激活失败请联系管理员！";
        }

        return msg;

    }


    @RequestMapping("/registerUser")
    @ResponseBody
    public ResultInfo registerUser(HttpServletRequest request, User user, String check, HttpSession session) {

        //从session中获取验证码

        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        session.removeAttribute("CHECKCODE_SERVER");

        try {
            if (!checkcode_server.equalsIgnoreCase(check)) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("验证码错误");
                return resultInfo;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("请刷新验证码之后重新注册");
            return resultInfo;
        }


        boolean flag = userService.regist(user);

        if (flag) {
            //注册成功
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败,用户名已存在");
        }
        return resultInfo;
    }


}
