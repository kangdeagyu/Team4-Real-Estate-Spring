package com.team4.spring_team4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team4.spring_team4.model.BusDto;

public class BusDaoImpl implements BusDao {
    
    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.BusDao";

    @Override
    public List<BusDto> listDao() throws Exception {
        return sqlSession.selectList(namespace + ".listDao");
    }
}
