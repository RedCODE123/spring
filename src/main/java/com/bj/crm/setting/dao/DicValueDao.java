package com.bj.crm.setting.dao;

import com.bj.crm.setting.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getListCode(String code);
}
