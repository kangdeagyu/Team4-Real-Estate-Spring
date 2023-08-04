package com.team4.spring_team4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team4.spring_team4.model.testDto;

public class testDaoImpl implements testDao{

    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.testDao";

    @Override
    public List<testDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList(namespace + ".listDao");
    }
    
}
