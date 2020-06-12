package cn.fy.service.impl;

import cn.fy.dao.CategoryDao;
import cn.fy.domain.Category;
import cn.fy.service.CategoryService;
import cn.fy.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private  CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        Jedis jedis=null;
        Set<Tuple> categorys=null;
        List<Category> cs=null;
        try{
             jedis = JedisUtil.getJedis();
             categorys = jedis.zrangeWithScores("category", 0, -1);
            if (categorys ==null ||categorys.size() ==0){
                cs=categoryDao.findAll();
                System.out.println("数据库中查询");
                //将集合数据存储redis中的category的key
                for (int i = 0; i < cs.size(); i++) {
                    jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
                }
            }else {
                System.out.println("从redis中查询");
                //这里是为了把set集合转成list集合
                cs=new ArrayList<Category>();
                for (Tuple tuple : categorys) {
                    Category category = new Category();
                    category.setCname(tuple.getElement());
                    category.setCid((int)tuple.getScore());
                    cs.add(category);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            //只要出异常了就是redis的服务器没开，那就从数据库中查询吧
            return categoryDao.findAll();
        }

        //使用srotedset排序查询
        //  Set<String> categorys = jedis.zrange("category", 0, -1);
        //查询sortedset中的分数（cid）和值（cname）

        //2.判断查询的集合是否为空
        //3.如果为空，从数据库中查询，再将数据存入redis，如果不为空直接返回

        return cs;
    }
}
