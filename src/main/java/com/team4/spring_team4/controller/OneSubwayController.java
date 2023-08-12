package com.team4.spring_team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.spring_team4.function.Haversine;
import com.team4.spring_team4.model.SubwayDto;
import com.team4.spring_team4.service.SubwayDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class OneSubwayController {

    @Autowired
    SubwayDaoService service;

    @RequestMapping("/ShortestStation")
    public String getShortestStation(HttpServletRequest request) throws Exception {

        /*
        타입 : get
        필수값
            xVal(Double), yVal(Double) : 경도, 위도
        
        사용 예시 : "localhost:8080/ShortestStation?xVal=127.049878493722&yVal=37.4987776755024"
        */

        Double shortestDistance = Double.MAX_VALUE;
        String stationName = "";
        String line = "";

        double xValue = Double.parseDouble(request.getParameter("xVal"));
        double yValue = Double.parseDouble(request.getParameter("yVal"));
        String type = request.getParameter("type");

        // String resultString = ""; // type에 따라 다른 값 리턴 위한 변수 선언

        List<SubwayDto> subwayList = service.listDao();

        JSONObject resultJSON = new JSONObject();

        for (int i = 0; i < subwayList.size(); i++) {
            double distance = Haversine.getDistance(
                    xValue,
                    yValue,
                    subwayList.get(i).getX(),
                    subwayList.get(i).getY());

            if (shortestDistance > distance) {
                shortestDistance = distance;
                stationName = subwayList.get(i).getName();
                line = subwayList.get(i).getLine();
                // System.out.println(distance);
            }
        }

        // resultString = (type == null || type.equals("distance")) // type값에 따라 다른 값 반환
        //             ? Double.toString(shortestDistance * -1000)
        //             : (type.equals("station")) ? stationName : "FAIL";

        // System.out.println(resultString);

        resultJSON.put("distance", shortestDistance * -1000);
        resultJSON.put("station", stationName);
        resultJSON.put("line", line);

        return resultJSON.toString();
    }

}
