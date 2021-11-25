package com.bj.crm.handler;

import com.bj.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginExceptionHandler {
    @ExceptionHandler (LoginException.class)
    @ResponseBody //将其转换为json对象
    public Map<String,Object> login(Exception e){
       e.printStackTrace();
       String msg=e.getMessage();//将消息封装传给页面
       Map<String,Object> map =new HashMap<>();
       map.put("success",false);
       map.put("msg",msg);
       return map;
    }
}
