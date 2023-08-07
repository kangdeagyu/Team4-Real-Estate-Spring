package com.team4.spring_team4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.busDao;
import com.team4.spring_team4.model.busDto;

@Service
public class busDaoServiceImpl implements busDaoService {

    @Autowired
    busDao dao;

    @Override
    public List<busDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return dao.listDao();
    }
    
}
