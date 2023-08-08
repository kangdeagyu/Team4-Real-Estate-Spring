package com.team4.spring_team4.dao;

import org.apache.ibatis.session.SqlSession;

public class UserDaoImpl implements UserDao{

    SqlSession sqlSession;
    public static String namespace = "com.team4.spring_team4.dao.UserDao";

    @Override
    public int dupCheck(String userid) throws Exception {
        return sqlSession.selectOne(namespace + ".dupCheck");
    }

    public int loginCheck(String userid, String password) throws Exception {
        return sqlSession.selectOne(namespace + ".loginCheck");
    }
    

}
