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
    public String getUserCount(HttpServletRequest request) throws Exception{

        int count = 0;
        String userid = request.getParameter("userid");
        count = service.dupCheck(userid);
        JSONObject resultJSON = new JSONObject();

        resultJSON.put("result", count);

        return resultJSON.toString();
    }
    
} // End
