package com.bj.crm.workbench.service.impl;

import com.bj.crm.workbench.dao.ClueDao;
import com.bj.crm.workbench.domain.Clue;
import com.bj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    @Qualifier("clueDao")
    private ClueDao clueDao;

    public ClueServiceImpl(ClueDao clueDao) {
        this.clueDao = clueDao;
    }

    @Override
    public boolean save(Clue clue) {
        boolean f=true;
        int m=clueDao.save(clue);
        if(m!=1){
            f=false;
        }
        return f;
    }

    @Override
    public Clue detail(String id) {
        Clue clue=clueDao.detail(id);
        return clue;
    }
}
