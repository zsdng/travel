package cn.fy.controller;

import cn.fy.dao.CategoryDao;
import cn.fy.domain.Category;
import cn.fy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/findAll")
    @ResponseBody
    public List<Category> findAll(){

        return categoryService.findAll();
    }
}
