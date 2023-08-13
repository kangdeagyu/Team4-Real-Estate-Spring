package com.team4.spring_team4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.spring_team4.service.RServiceImpl;

@Controller
public class RDSController {
    
    @Autowired
    private RServiceImpl rService;

    @RequestMapping("/rdsRServe")
    @ResponseBody
    public String rPredict(
        @RequestParam double busStations, @RequestParam double distance,
        @RequestParam double leaseableArea, @RequestParam double floor,
        @RequestParam double yoc, @RequestParam double contractDate,
        @RequestParam double baseRate, @RequestParam double x,
        @RequestParam double y, @RequestParam String size, @RequestParam String isSale) {
        
        String predictionResult = rService.rPredict(busStations, distance, leaseableArea, floor, yoc, contractDate, baseRate, x, y, size, isSale);
        return "{\"result\":\"" + predictionResult + "\"}";
    }
}
