package com.team4.spring_team4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team4.spring_team4.model.TestDto;

public class TestDaoImpl implements TestDao{

    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.TestDao";

    @Override
    public List<TestDto> listDao() throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList(namespace + ".listDao");
    }
    
}
