package com.team4.spring_team4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.spring_team4.dao.UserDao;

@Service
public class UserDaoServiceImpl implements UserDaoService{

    @Autowired
    UserDao dao;
    
    @Override
    public int dupCheck(String userid) throws Exception {
        return dao.dupCheck(userid);
    }
    
}
