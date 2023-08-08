package com.team4.spring_team4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.BusDao;
import com.team4.spring_team4.model.BusDto;

@Service
public class BusDaoServiceImpl implements BusDaoService {

    @Autowired
    BusDao dao;

    @Override
    public List<BusDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return dao.listDao();
    }
    
}
