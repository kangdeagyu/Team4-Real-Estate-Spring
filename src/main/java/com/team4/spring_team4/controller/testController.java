package com.team4.spring_team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.team4.spring_team4.model.TestDto;
import com.team4.spring_team4.service.TestDaoService;


@Controller
public class TestController{

    @Autowired
    TestDaoService service;

    //@ResponseBody -> 페이지로 데이터를 뿌려서 넘기는 방식이 아닌 JSON Data를 바로 넘겨줄 수 있다.
    @RequestMapping("/get")
    public String listQuery(Model model) throws Exception{
        List<TestDto> listDao = service.listDao();

        Gson gson = new Gson();
        String listGson = gson.toJson(listDao);
		model.addAttribute("list", listGson);
        // return listGson;
        return "getXY";
    }

}
