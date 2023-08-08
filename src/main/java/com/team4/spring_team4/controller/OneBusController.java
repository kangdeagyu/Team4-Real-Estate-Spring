package com.team4.spring_team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.spring_team4.function.Haversine;
import com.team4.spring_team4.model.BusDto;
import com.team4.spring_team4.service.BusDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OneBusController {

    @Autowired
    BusDaoService service;
    
    @RequestMapping("/busCount")
    @ResponseBody
    public String getBusCount(HttpServletRequest request) throws Exception{

        /*
        * 사용법 : get방식으로 x값(변수명: xVal), y값(변수명: yVal) 넘겨주면 반경 300미터 안의 버스정류장 개수를 return해줌
        * 사용 예시 : "localhost:8080/busCount?xVal=127.049878493722&yVal=37.4987776755024"
        */

        int count = 0;

        double xValue = Double.parseDouble(request.getParameter("xVal"));
        double yValue = Double.parseDouble(request.getParameter("yVal"));

        List<BusDto> busList = service.listDao();

        JSONObject resultJSON = new JSONObject();
        
        for (int i = 0; i < busList.size(); i++) {
            double distance = Haversine.getDistance(
                    xValue,
                    yValue,
                    busList.get(i).getX(),
                    busList.get(i).getY()
                );

            if (distance * 1000 <= 300) {
                count++;
                //System.out.println(distance);
            }
        }

        System.out.println(resultJSON);
        
        resultJSON.put("result", count);
        
        return resultJSON.toString();
    }
}
