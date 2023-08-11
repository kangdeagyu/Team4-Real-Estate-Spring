package com.team4.spring_team4.service;


public interface UserDaoService {

    public int dupCheck(String userid) throws Exception;
    public int loginCheck(String userid, String password) throws Exception;
}
