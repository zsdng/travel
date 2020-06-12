package cn.fy.service;

import cn.fy.domain.Route;
import cn.fy.utils.PageUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RouteService  {

    public List<Route> findByCid(Integer cid,String rname);
}
