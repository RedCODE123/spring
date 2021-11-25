package com.bj.crm.setting.web.controller;

import com.bj.crm.exception.LoginException;
import com.bj.crm.setting.domain.User;
import com.bj.crm.setting.service.UserService;
import com.bj.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/setting/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService us;
    public void setUs(UserService us){
        this.us=us;
    }
    @RequestMapping(value = "/login.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    protected Map<String,Object> service(HttpServletRequest request,User user)throws LoginException {
        System.out.println("进入到用户登录控制器中");
        System.out.println("进入到验证登陆操作");
        String loginAct=user.getLoginAct();
        String loginPwd=user.getLoginPwd();
        //将密码有明文转为密文
        loginPwd=MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip=request.getRemoteAddr();
        System.out.println("---ip: "+ip);
        Map<String,Object> map=new HashMap<>();
        user =us.login(loginAct,loginPwd,ip);
        map.put("success",true);
        request.getSession().setAttribute("user",user);
        return map;
    }
    @RequestMapping("/register.do")
    public void register() {
        System.out.println("进入到用户注册的控制器");
    }
}

