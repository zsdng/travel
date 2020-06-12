package cn.fy.dao;

import cn.fy.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SellerDao {

    @Select("select * from seller where sid=#{id}")
    public Seller findBySid(Integer sid);
}
