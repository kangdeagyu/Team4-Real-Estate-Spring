package com.team4.spring_team4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.subwayDao;
import com.team4.spring_team4.model.subwayDto;

@Service
public class subwayDaoServiceImpl implements subwayDaoService {

    @Autowired
    subwayDao dao;

    @Override
    public List<subwayDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return dao.listDao();
    }
    
}
