package com.bj.crm.setting.service.impl;

import com.bj.crm.exception.LoginException;
import com.bj.crm.setting.dao.UserDao;
import com.bj.crm.setting.domain.User;
import com.bj.crm.setting.service.UserService;
import com.bj.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);

        if(user==null){
            throw new LoginException("账号密码错误");
        }
        //如果成功执行了上述操作
        //需要向下验证其他项
        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        // compareTo()方法比较大小
        if(expireTime.compareTo(currentTime)<0){
            throw new LoginException("账号已失效");
        }
        //判断锁定状态
        String lockState = user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("账号已经锁定");
        }
        //判断ip地址

        String allowIps=user.getAllowIps();

        if(!allowIps.contains(ip)){
            throw new LoginException("ip地址受限");
        }
        return user;
    }

    public List<User> getUserList(){
        List<User> ulist= userDao.getUserList();
        return ulist;
    }
}
