package com.bj.crm.workbench.web.controller;

import com.bj.crm.setting.domain.User;
import com.bj.crm.setting.service.UserService;

import com.bj.crm.utils.DateTimeUtil;
import com.bj.crm.utils.UUIDUtil;
import com.bj.crm.workbench.domain.Clue;
import com.bj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/workbench/clue")
public class ClueController {
    @Autowired
    @Qualifier("clueService")
    private ClueService cs;

    @Autowired
    @Qualifier("userService")
    private UserService us;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    private List<User> getUserList(){
        System.out.println("进入到线索控制器");

        System.out.println("取得用户信息列表");
        List<User> ulist =us.getUserList();
        return ulist;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    private boolean save(HttpSession session, Clue clue){
        System.out.println("进入到线索控制器");

        System.out.println("执行线索的添加操作");
        String id = UUIDUtil.getUUID();
        String createBy = ((User)session.getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        clue.setId(id);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        boolean  f= cs.save(clue);
        return f;
    }
    @RequestMapping("/detail.do")
    private ModelAndView detail(String id){
        System.out.println("进入到线索控制器");

        System.out.println("跳转到线索的详细信息页");
        Clue clue = cs.detail(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("c",clue);
        mv.setViewName("forward:/workbench/clue/detail.jsp");
        return mv;

    }

}

