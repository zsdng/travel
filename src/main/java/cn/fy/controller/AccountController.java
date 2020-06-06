package cn.fy.controller;

import cn.fy.domain.Account;
import cn.fy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: Fy
 *账户web层
 * @create: 2020-04-16 10:15
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/findAll")
    public String findAll(Model model ){
        System.out.println("Controller中的方法执行啦！");
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        list.forEach(System.out::println);
        return "success";
    }
    @RequestMapping("/addAccount")
    public String addAccount(Account account  , HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        accountService.addAccount(account);
        request.getRequestDispatcher("findAll").forward(request,response);
        return "success";
    }

}
