package com.team4.spring_team4.dao;

import java.util.List;

import com.team4.spring_team4.model.TestDto;

public interface TestDao {
    
    public List<TestDto> listDao() throws Exception;
}

