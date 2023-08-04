package com.team4.spring_team4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.testDao;
import com.team4.spring_team4.model.testDto;

@Service
public class testDaoServiceImpl implements testDaoService{

    @Autowired
    testDao dao;

    @Override
    public List<testDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return dao.listDao();
    }

    
    
}
