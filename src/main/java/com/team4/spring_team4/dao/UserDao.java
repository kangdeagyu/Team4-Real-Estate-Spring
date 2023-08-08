package com.team4.spring_team4.dao;

public interface UserDao {
    
    public int dupCheck(String userid) throws Exception;
    public int loginCheck(String userid, String password) throws Exception;
}
