package com.bj.crm.setting.service.impl;

import com.bj.crm.setting.dao.DicTypeDao;
import com.bj.crm.setting.dao.DicValueDao;
import com.bj.crm.setting.dao.UserDao;
import com.bj.crm.setting.domain.DicType;
import com.bj.crm.setting.domain.DicValue;
import com.bj.crm.setting.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dicService")
public class DicServiceImpl implements DicService {
    @Autowired
    @Qualifier("dicTypeDao")
    private DicTypeDao dicTypeDao;
    @Autowired
    @Qualifier("dicValueDao")
    private DicValueDao dicValueDao;
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public Map<String, List<DicValue>> getAll() {
         Map<String,List<DicValue>> map=new HashMap<>();
         List<DicType> dtList=dicTypeDao.getTypeList();
         //将字典类型进行遍历
        for(DicType d:dtList){
            String code=d.getCode();
            //根据字典类型来取得字典值的列表
            List<DicValue> dv=dicValueDao.getListCode(code);
            map.put(code+"List",dv);
        }
        return map;
    }
}
