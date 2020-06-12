package cn.fy.service.impl;

import cn.fy.dao.RouteDao;
import cn.fy.domain.Route;
import cn.fy.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Override
    public List<Route> findByCid(Integer cid,String rname) {
//        PageHelper.startPage(pageUtils.getPageIndex(),pageUtils.getPageSize());
        Route route =new Route();
        if (cid!=null){
            route.setCid(cid);
        }
        if (rname!=null){
            route.setRname(rname);
        }
        return  routeDao.findByCid(route);


    }
}
