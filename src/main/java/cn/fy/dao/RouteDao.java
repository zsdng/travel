package cn.fy.dao;

import cn.fy.domain.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteDao {

    public List<Route> findByCid(Route route);

    public Route findOneByRid(Integer rid);
}
