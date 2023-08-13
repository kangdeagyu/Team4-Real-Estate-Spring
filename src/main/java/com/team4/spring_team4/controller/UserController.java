package com.team4.spring_team4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.spring_team4.service.UserDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class UserController {

    @Autowired
    UserDaoService service;

    @RequestMapping("/dupCheck")
    public String getDupCount(HttpServletRequest request) throws Exception{

        int count = 2;
        String userid = request.getParameter("userid");        
        if(userid == null){
            count = 2;
        }else{
            count = service.dupCheck(userid);
        }
        JSONObject resultJSON = new JSONObject();

        resultJSON.put("result", count == 0 ? "success" : "fail");

        return resultJSON.toString();
    }


    @RequestMapping("/loginCheck")
    public String getUserCount(HttpServletRequest request) throws Exception{

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        int count = service.loginCheck(userid, password);
        JSONObject resultJSON = new JSONObject();

        System.out.println("count : " + count);
        resultJSON.put("result", (count == 0) ? "fail" : "success");

        return resultJSON.toString();
    }
    
} // End
