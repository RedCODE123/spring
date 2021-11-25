package com.bj.crm.web.listener;

import com.bj.crm.setting.domain.DicValue;
import com.bj.crm.setting.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {

    /**
     * 该方法是用来监听上下文域对象的方法，当服务器启动，上下文域对象创建
     * 对象创建完毕后，马上执行该方法
     * event: 该参数能取得监听对象
     *          监听的是什么对象，我们就能取得什么对象
     *          例如我们监听是上下文域对象。通过改参数就能取得上下文域对象
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application=servletContextEvent.getServletContext();

        DicService service= WebApplicationContextUtils.getWebApplicationContext(application).getBean(DicService.class);


        //取数据字典
        //application.setAttribute("",);
        /**
         * 应该管业务层要什么
         *  7个List
         *
         *  可以打包称为一个map
         *  业务层应该是这样来保存数据的:
         *      map.put("appellationList",dvList1);
         *      map.put("clueStateList",dvList2);
         *      map.put("stageList",dvList3);
         */
        Map<String, List<DicValue>> map = service.getAll();
        //将map中解析为上下文作用域中保存的键值对
       Set<String>keyset =map.keySet();
       for(String key:keyset){
            application.setAttribute(key,map.get(key));
       }

    }





    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("上下文域对象销毁了");
    }
}
