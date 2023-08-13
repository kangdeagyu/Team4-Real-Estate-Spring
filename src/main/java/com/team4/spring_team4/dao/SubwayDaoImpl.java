package com.team4.spring_team4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team4.spring_team4.model.SubwayDto;

public class SubwayDaoImpl implements SubwayDao {

    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.SubwayDao";

    @Override
    public List<SubwayDto> listDao() throws Exception {
        return sqlSession.selectList(namespace + ".listDao");
    }
    
}
