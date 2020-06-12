package cn.fy.service.impl;

import cn.fy.dao.RouteDao;
import cn.fy.dao.RouteImgDao;
import cn.fy.dao.SellerDao;
import cn.fy.domain.Route;
import cn.fy.domain.RouteImg;
import cn.fy.domain.Seller;
import cn.fy.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private RouteImgDao routeImgDao;

    @Autowired
    private SellerDao sellerDao;

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

    /**
     * 这里会把其他的信息也查询并添加进去
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(Integer rid) {
        //1.根据id去route表中查询route对象
        Route route = routeDao.findOneByRid(rid);
        List<RouteImg> routeImgList = routeImgDao.findByRid(rid);
        //把图片集合设置进去
        route.setRouteImgList(routeImgList);
        //根据route的sid（商家的id）查询商家对象
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);
        return route;
    }
}
