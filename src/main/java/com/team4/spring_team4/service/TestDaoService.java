package com.team4.spring_team4.service;

import java.util.List;

import com.team4.spring_team4.model.TestDto;

public interface TestDaoService{

    public List<TestDto> listDao() throws Exception;
}