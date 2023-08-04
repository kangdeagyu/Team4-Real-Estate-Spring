package com.team4.spring_team4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team4.spring_team4.model.subwayDto;

public class subwayDaoImpl implements subwayDao {

    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.subwayDao";

    @Override
    public List<subwayDto> listDao() throws Exception {
        return sqlSession.selectList(namespace + ".listDao");
    }
    
}
