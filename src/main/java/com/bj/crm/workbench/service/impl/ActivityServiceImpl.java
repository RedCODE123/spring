package com.bj.crm.workbench.service.impl;

import com.bj.crm.setting.dao.UserDao;
import com.bj.crm.setting.domain.User;
import com.bj.crm.vo.PaginationVo;
import com.bj.crm.workbench.dao.ActivityDao;
import com.bj.crm.workbench.dao.ActivityRemarkDao;
import com.bj.crm.workbench.domain.Activity;
import com.bj.crm.workbench.domain.ActivityRemark;
import com.bj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    @Qualifier("activityDao")
    private ActivityDao activityDao;

    @Autowired
    @Qualifier("activityRemarkDao")
    private ActivityRemarkDao activityRemarkDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setActivityRemarkDao(ActivityRemarkDao activityRemarkDao) {
        this.activityRemarkDao = activityRemarkDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean save(Activity activity) {
        boolean f=true;
        int count=activityDao.save(activity);
        if(count!=1){
            f=false;
        }
        return f;
    }

    @Override
    public PaginationVo<Activity> pageList(Map<String, Object> map) {
        //取得total
        int total= activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList=activityDao.getActivityByCondition(map);
        //将total和dataList封装到vo中
        PaginationVo<Activity> vo=new PaginationVo<>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }

    @Override
    public boolean delete(String[] ids) {
        boolean f = true;
        //查询出需要删除的备注的数量
        int count= activityRemarkDao.getCountByAids(ids);
        //删除备注，返回受到影响的条数
        int count2=activityRemarkDao.deleteByAids(ids);
        //删除市场活动
        if(count!=count2){
            f=false;
        }
        int count3=activityDao.delete(ids);
        if(count3!=ids.length){
            f=false;
        }
        return f;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        Map<String,Object> map=new HashMap<String,Object>();
        // 取uList
        List<User> uList = userDao.getUserList();
        // 取a
        Activity a = activityDao.getById(id);
        map.put("uList",uList);
        map.put("a",a);
        return map;
    }
    @Override
    public boolean update(Activity a) {
        boolean flag = true;
        int result = activityDao.update(a);
        if(result != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public Activity detail(String id) {
        Activity activity=activityDao.detail(id);
        return activity;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        List<ActivityRemark> activityRemarks=activityRemarkDao.getRemarkListByAid(activityId);
        return activityRemarks;
    }

    @Override
    public boolean deleteRemarkById(String id) {
        boolean f=true;
        int result = activityRemarkDao.deleteRemarkById(id);
        if(result!=1){
            f=false;
        }
        return f;
    }

    @Override
    public boolean saveRemark(ActivityRemark ar) {
        boolean f=true;
        int result=activityRemarkDao.saveRemark(ar);
        if(result!=1){
            f=false;
        }
        return f;
    }

    @Override
    public boolean updateRemark(ActivityRemark ar) {
        boolean f=true;
        int result=activityRemarkDao.updateRemark(ar);
        if(result!=1){
            f=false;
        }
        return f;
    }

}
