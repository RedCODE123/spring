package com.bj.crm.workbench.service;

import com.bj.crm.vo.PaginationVo;
import com.bj.crm.workbench.domain.Activity;
import com.bj.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    boolean save(Activity activity);

    PaginationVo<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    boolean update(Activity a);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    boolean deleteRemarkById(String id);

    boolean saveRemark(ActivityRemark ar);

    boolean updateRemark(ActivityRemark ar);
}
