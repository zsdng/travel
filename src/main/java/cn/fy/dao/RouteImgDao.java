package cn.fy.dao;

import cn.fy.domain.RouteImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteImgDao {

    @Select("select * from routeimg where rid=#{rid}")
    public List<RouteImg> findByRid(Integer rid);
}
