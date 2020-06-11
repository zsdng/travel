package cn.fy.controller;

import cn.fy.domain.Route;
import cn.fy.service.RouteService;
import cn.fy.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;


    @RequestMapping("/findByCid")
    @ResponseBody
    public PageInfo<Route> findByCid(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize, Integer cid){
        PageHelper.startPage(pageNum,pageSize);
        List<Route> list = routeService.findByCid(cid);
        return new PageInfo<Route>(list);
    }



}
