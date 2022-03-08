package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lijian.constant.RedisConstant;
import com.lijian.dao.SetmealDao;
import com.lijian.entity.PageResult;
import com.lijian.pojo.Setmeal;
import com.lijian.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 添加新的套餐
     *
     * @param travelGroupIds 用户选取的套餐游中跟团游的id值
     * @param setmeal        用户的套餐数据
     */
    @Override
    public void add(Integer[] travelGroupIds, Setmeal setmeal) {
        //将套餐数据存储到数据库中
        setmealDao.add(setmeal);
        //向套餐和跟团游的中间表中插入数据
        if (travelGroupIds != null && travelGroupIds.length > 0) {
            //绑定套餐和跟团游的多对多关系
            setTravelGroupAndSetmeal(setmeal.getId(), travelGroupIds);
        }
        //将图片的名称保存到Redis数据库中
        savePic2Redis(setmeal.getImg());

    }

    public void setTravelGroupAndSetmeal(Integer id, Integer[] travelGroupIds) {
        for (Integer checkTravelGroupId : travelGroupIds) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("setmeal_id", id);
            map.put("travelgroup_id", checkTravelGroupId);
            setmealDao.addTravelGroupAndSetmeal(map);
        }
    }

    /**
     * 将套餐游数据存储到数据库之后，将图片的名称存储到Redis中的基于Set的集合中
     * @param pic  图片的名称
     */
    private void savePic2Redis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }

    /**
     * y页面数据的分页
     * @param queryString   查询的条件
     * @param pageSize      一页能够容纳的数据量
     * @param currentPage   当前的页号
     * @return
     */
    @Override
    public PageResult findPage(String queryString, Integer pageSize, Integer currentPage) {
        //借助Mybatis的分页插件进行分页
        PageHelper.startPage(currentPage,pageSize);
        //查询分页的结果
        Page<Setmeal> page  = setmealDao.findPage(queryString);
        //将Page转换成pageResult返回
        return new PageResult(page.getTotal(),page.getResult());
    }


    /**
     * 将所有的套餐列表数据查询出来，并传送给移动端进行展示
     * @return   所有的套餐列表数据
     */
    @Override
    public List<Setmeal> findAll() {
        List <Setmeal>  res  = setmealDao.findAll();
        return res;
    }


    /**
     * 根据id套餐游的信息
     * @param id  传入的套餐游的id
     * @return  返回对应的套餐游数据
     */
    @Override
    public Setmeal findById(int id) {
        Setmeal setmeal = setmealDao.findById(id);
        return setmeal;
    }
}
