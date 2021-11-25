package com.bj.crm.workbench.service;

import com.bj.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueService {
    boolean save(Clue clue);

    Clue detail(String id);
}
