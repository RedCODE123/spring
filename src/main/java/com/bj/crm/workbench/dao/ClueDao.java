package com.bj.crm.workbench.dao;

import com.bj.crm.workbench.domain.Clue;

public interface ClueDao {

    int save(Clue clue);

    Clue detail(String id);
}
