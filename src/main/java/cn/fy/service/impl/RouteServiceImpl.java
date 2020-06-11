package cn.fy.service.impl;

import cn.fy.dao.RouteDao;
import cn.fy.domain.Route;
import cn.fy.service.RouteService;
import cn.fy.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Override
    public List<Route> findByCid(Integer cid) {
//        PageHelper.startPage(pageUtils.getPageIndex(),pageUtils.getPageSize());
        return  routeDao.findByCid(cid);


    }
}
