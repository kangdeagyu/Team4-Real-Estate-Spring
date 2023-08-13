package com.team4.spring_team4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.TestDao;
import com.team4.spring_team4.model.TestDto;

@Service
public class TestDaoServiceImpl implements TestDaoService{

    @Autowired
    TestDao dao;

    @Override
    public List<TestDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return dao.listDao();
    }

    
    
}
