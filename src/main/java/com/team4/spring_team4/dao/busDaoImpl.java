package com.team4.spring_team4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team4.spring_team4.model.busDto;

public class busDaoImpl implements busDao {
    
    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.busDao";

    @Override
    public List<busDto> listDao() throws Exception {
        return sqlSession.selectList(namespace + ".listDao");
    }
}
