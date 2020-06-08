package cn.fy.controller;

import cn.fy.domain.ResultInfo;
import cn.fy.domain.User;
import cn.fy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResultInfo resultInfo;

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    @RequestMapping("/findLogin")
    @ResponseBody
    public ResultInfo findLogin(HttpSession session){
        String action = (String) session.getAttribute("action");
        if (action == null) {
            action = "login_out";
        }
        if (action.equalsIgnoreCase("login")) {
            resultInfo.setFlag(true);
            return resultInfo;
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("游客状态");
            return resultInfo;
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){

        session.invalidate();

        return "redirect:login.html";
    }


    @RequestMapping("/loginCheck")
    @ResponseBody
    public ResultInfo loginCheck(User user, HttpSession session, String check,String action) {
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
            resultInfo.setErrorMsg("请刷新验证码之后重新登录");
            return resultInfo;
        }
        try {
            Integer flag = userService.loginCheck(user);

            if (flag == 5) {
                resultInfo.setErrorMsg("用户名或密码错误");
                resultInfo.setFlag(false);
                return resultInfo;
            } else if (flag == 6) {
                resultInfo.setErrorMsg("用户未激活,请激活后重新登录！");
                return resultInfo;

            } else if (flag == 666) {
                resultInfo.setErrorMsg("登录成功，正在为您跳转");
                resultInfo.setFlag(true);
                User byUsername = userService.findByUsername(user.getUsername());
                session.setAttribute("user", byUsername);
                session.setAttribute("action", action);
                return resultInfo;
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("登录失败，请联系管理员fy");
                return resultInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("登录失败，请联系管理员fy");
            return resultInfo;

        }

    }

    @RequestMapping("/checkCode")
    public void checkCodeGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0, 0, width, height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体", Font.BOLD, 24));
        //向图片上写入验证码
        g.drawString(checkCode, 15, 25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", response.getOutputStream());

    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
//        String base = "0123456789ABCDEFGabcdefg";
        String base = "00000000";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

}
