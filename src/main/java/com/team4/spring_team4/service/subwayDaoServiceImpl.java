package com.team4.spring_team4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.SubwayDao;
import com.team4.spring_team4.model.SubwayDto;

@Service
public class SubwayDaoServiceImpl implements SubwayDaoService {

    @Autowired
    SubwayDao dao;

    @Override
    public List<SubwayDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return dao.listDao();
    }
    
}
