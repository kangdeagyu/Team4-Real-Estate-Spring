package com.team4.spring_team4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.spring_team4.function.Haversine;
import com.team4.spring_team4.model.SubwayDto;
import com.team4.spring_team4.model.XY;
import com.team4.spring_team4.service.SubwayDaoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class SubwayController {

    @Autowired
    SubwayDaoService service;

    @RequestMapping("/subway")
    public String getDistance(Model model) throws Exception {
        // Read CSV
        String csvFile = "static/csv/개포동.csv";
        String line;
        
        List<XY> xyList = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            int i = 0;
            while ((line = br.readLine()) != null) {
                XY xandy = new XY();
                String[] data = line.split(",");
                if (i == 0) {
                    i++;
                    continue;
                }
                xandy.setAddress(data[0]);
                xandy.setX(Double.parseDouble(data[1]));
                xandy.setY(Double.parseDouble(data[2]));
                xyList.add(i - 1, xandy);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Model
        List<SubwayDto> subwayPoint = service.listDao();
        // model.addAttribute("subway", listDao);


        for (int i = 0; i < xyList.size(); i++) {
            // System.out.println(xyList.get(i).getX());

            double shortestDistance = Double.MAX_VALUE; // 각 건물에 대한 최단 거리 초기화
            String lines = "";
            String stationName = "";
            String address = "";
            for (int j = 0; j < subwayPoint.size(); j++) {
                double distance = Haversine.getDistance(
                        xyList.get(i).getX(),
                        xyList.get(i).getY(),
                        subwayPoint.get(j).getX(),
                        subwayPoint.get(j).getY()
                    );

                if (shortestDistance > distance) {
                    shortestDistance = distance;
                    lines = subwayPoint.get(j).getLine();
                    stationName = subwayPoint.get(j).getName();
                    address = xyList.get(i).getAddress();
                    //System.out.println(distance);
                }
            }

            System.out.println(address + ',' + lines + ',' + stationName + ',' + -(shortestDistance * 1000));

        }

        return "getDistance";
    }

    @RequestMapping("/getXY")
    public String getXY(Model model) throws Exception {
        String csvFile = "static/csv/road.csv";
        String line;
        
        List<String> addressList = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (i == 0) {
                    i++;
                    continue;
                }
                addressList.add(i - 1, data[0]);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("list", addressList);
        return "getXY";
    }

}
