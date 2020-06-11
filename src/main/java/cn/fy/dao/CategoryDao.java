package cn.fy.dao;

import cn.fy.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {


    public List<Category> findAll();
}
