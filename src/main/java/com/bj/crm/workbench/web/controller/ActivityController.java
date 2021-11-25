package com.bj.crm.workbench.web.controller;

import com.bj.crm.exception.LoginException;
import com.bj.crm.setting.domain.User;
import com.bj.crm.setting.service.UserService;
import com.bj.crm.utils.DateTimeUtil;
import com.bj.crm.utils.UUIDUtil;
import com.bj.crm.vo.PaginationVo;
import com.bj.crm.workbench.domain.Activity;
import com.bj.crm.workbench.domain.ActivityRemark;
import com.bj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {
    @Autowired
    @Qualifier("userService")
    private UserService us;
    @Autowired
    @Qualifier("activityService")
    private ActivityService as;
    public void setUs(UserService us){
        this.us=us;
    }

    public ActivityController(ActivityService as) {
        this.as = as;
    }
    @RequestMapping("/getUserList.do")
    @ResponseBody
    private List<User> getUserList(){
        System.out.println("进入到市场活动控制器");
        System.out.println("取得用户信息列表");
        List<User> user = us.getUserList();
        return user;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    private boolean save(HttpSession session, Activity activity){
        System.out.println("执行是市场活动的添加操作");
        String id= UUIDUtil.getUUID();
        //创建当前系统的时间
        String createTime= DateTimeUtil.getSysTime();
        //创建人，当前登录用户
        String createBy=((User)session.getAttribute("user")).getName();
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        boolean f=as.save(activity);
        return f;
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    private PaginationVo<Activity> pageList(Activity activity,Integer pageSize, Integer pageNo){
        System.out.println("进入到市场活动控制器");
        System.out.println("进入到查询市场活动信息列表的操作(结合条件查询+分页查询)");
        String name=activity.getName();
        String owner=activity.getOwner();
        String startDate=activity.getStartDate();
        String endDate=activity.getEndDate();

        int skipCount=(pageNo-1)*pageSize;
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("pageSize", pageSize);
        map.put("skipCount", skipCount);
        PaginationVo<Activity> activityPaginationVo=as.pageList(map);
        return activityPaginationVo;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    private boolean delete(@RequestParam(value = "id") String[] ids){
        System.out.println("执行市场活动的删除操作");
        boolean f=as.delete(ids);
        return f;
    }
    @RequestMapping("/getUserListAndActivity.do")
    @ResponseBody
    private Map<String,Object> getUserListAndActivity(String id){
        System.out.println("进入到市场活动控制器");
        System.out.println("进入到查询用户信息列表和根据市场活动id查询单条记录的操作");
        Map<String,Object> map= as.getUserListAndActivity(id);
        return map;
    }
    @RequestMapping("/update.do")
    @ResponseBody
    private boolean update(HttpSession session,Activity a){
        System.out.println("进入到市场活动控制器");
        System.out.println("执行市场活动修改操作");
        // 修改时间: 当前系统时间
        String editTime = DateTimeUtil.getSysTime();
        // 修改人: 当前登录用户
        String editBy = ((User) session.getAttribute("user")).getName();

        a.setEditTime(editTime);
        a.setEditBy(editBy);
        boolean flag = as.update(a);
        return flag;
    }

    @RequestMapping("/detail.do")
    @ResponseBody
    private ModelAndView detail(String id){
        System.out.println("进入到市场活动控制器");
        System.out.println("进入到跳转到详细信息页的操作");
        Activity activity=as.detail(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject(activity);
        mv.setViewName("/activity/detail");
        System.out.println(mv);
        return mv;
    }

    @RequestMapping("/getRemarkListByAid.do")
    @ResponseBody
    private List<ActivityRemark> getRemarkListByAid(String activityId){
        System.out.println("根据市场活动id,取得备注信息");
        List<ActivityRemark> activityRemarks= as.getRemarkListByAid(activityId);
        return activityRemarks;
    }

    @RequestMapping("/deleteRemarkById.do")
    @ResponseBody
    private boolean deleteRemark(String id){
        System.out.println("进入到市场活动控制器");
        System.out.println("删除备注操作");
        boolean f=as.deleteRemarkById(id);
        return f;
    }
    @RequestMapping("/saveRemark.do")
    @ResponseBody
    private Map<String,Object> saveRemark(HttpSession session,ActivityRemark ar){
        System.out.println("进入到市场活动控制器");
        System.out.println("执行添加备注操作");
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) session.getAttribute("user")).getName();
        String editFlag = "0";
        ar.setId(id);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setEditFlag(editFlag);
        boolean flag = as.saveRemark(ar);
        Map<String,Object>map =new HashMap<>();
        map.put("success", flag);
        map.put("ar",ar);
        return map;
    }
    @RequestMapping("/updateRemark.do")
    @ResponseBody
    private Map<String,Object> updateRemark(HttpSession session,ActivityRemark ar){
        System.out.println("进入到市场活动控制器");
        System.out.println("执行修改备注的操作");

        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) session.getAttribute("user")).getName();
        String editFlag = "1";
        ar.setEditTime(createTime);
        ar.setEditBy(createBy);
        ar.setEditFlag(editFlag);
        boolean flag = as.updateRemark(ar);
        Map<String,Object>map =new HashMap<>();
        map.put("success", flag);
        map.put("ar",ar);
        return map;
    }
}

