package com.team4.spring_team4.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.team4.spring_team4.function.Haversine;
import com.team4.spring_team4.model.RoadNameDto;
import com.team4.spring_team4.model.SubwayDto;
import com.team4.spring_team4.model.XY;
import com.team4.spring_team4.service.SubwayDaoService;

@Controller
public class DistanceController {

    @Autowired
    SubwayDaoService service;
    
    @RequestMapping("/road_name")
    public List<XY> getRoadName(Model model) throws Exception{
        // Read CSV
        String csvFile = "static/csv/역삼동.csv";
        String line;

        List<XY> xyList = new ArrayList<>();

        List<RoadNameDto> roadList = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            int i = 0;
            while ((line = br.readLine()) != null) {
                RoadNameDto road = new RoadNameDto();
                String[] data = line.split(" ");
                for (int j = 0; j < data.length; j++) {
                    if (j == 0) {
                        road.setCity(data[j].replace("\"", ""));
                    } else if (j == 1) {
                        road.setGu(data[j]);
                    } else if (j == 2) {
                        road.setRoadName(data[j].replace("\"", "") + " " + data[j+1].replace("\"", ""));
                        j++;
                    } else {
                        if (road.getAptName() != null) {
                            road.setAptName(road.getAptName() + " " + data[j].replace("\"", ""));
                        } else {
                            road.setAptName(data[j].replace("\"", ""));
                        }
                    }
                }
                
                roadList.add(road);
                System.out.println("City: " + road.getCity());
                System.out.println("Gu: " + road.getGu());
                System.out.println("RoadName: " + road.getRoadName());
                System.out.println("AptName: " + road.getAptName());
                
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(roadList.size());
        List<String> road_address_name = new ArrayList<String>();

        try {
            String API_KEY = "ef894ee905a0643b7844daf7341d7569";
            String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=";

            for(int i = 0; i < roadList.size(); i++){
                XY xandy = new XY();

                // Thread.sleep(500);
                String roadName = roadList.get(i).getRoadName();
                String encodedRoadName = URLEncoder.encode(roadName, "UTF-8");

                URL url = new URL(apiUrl + encodedRoadName);
                // System.out.println(url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "KakaoAK " + API_KEY);
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader brd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String lines;
                    StringBuilder response = new StringBuilder();

                    while ((lines = brd.readLine()) != null) {
                        response.append(lines);
                        // System.out.println(response);
                    }
                    brd.close();

                    Gson gson = new Gson();
                    String result = response.toString();

                    // JSON 문자열을 Map으로 변환
                    Type type = new TypeToken<Map<String, Object>>() {}.getType();
                    Map<String, Object> jsonMap = gson.fromJson(result, type);

                    if (jsonMap.containsKey("documents")) {
                        List<Map<String, Object>> documents = (List<Map<String, Object>>) jsonMap.get("documents");
                        if (!documents.isEmpty()) {
                            Map<String, Object> document = documents.get(0);
                            if (document.containsKey("x") && document.containsKey("y")) {
                                String x = document.get("x").toString();
                                String y = document.get("y").toString();
                                // System.out.println("x: " + x + ", y: " + y);
                                xandy.setAddress(roadList.get(i).getRoadName());
                                xandy.setX(Double.parseDouble(x));
                                xandy.setY(Double.parseDouble(y));
                                xyList.add(xandy);

                                // "road_address" 안에 있는 "address_name" 값 가져오기
                                if (document.containsKey("road_address")) {
                                    Map<String, Object> roadAddress = (Map<String, Object>) document.get("road_address");
                                    if (roadAddress.containsKey("address_name")) {
                                        String nRoad_name = roadAddress.get("address_name").toString();
                                        road_address_name.add(nRoad_name);
                                    }
                                }
                            }
                        }
                    }
                    // System.out.println(xyList.get(i).getAddress());
                    // System.out.println(xyList.get(i).getX());
                    // System.out.println(xyList.get(i).getY());
                    // return xyList;

                } else {
                    System.out.println("HTTP GET request failed with response code: " + responseCode);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<SubwayDto> subwayPoint = service.listDao();

        String filePath = "C:\\ethan\\spring_team4\\src\\main\\resources\\static\\csv/resultOutput.csv";

        // FileWriter 객체 생성
        FileWriter writer = new FileWriter(filePath);

        // Header 추가
        String headerCsv = "도로명, 지번, 호선, 역이름, x, y, 거리";
        writer.write(headerCsv);
        writer.write("\n");

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

            // System.out.println(road_address_name.get(i).toString() + "," + address + ',' + lines + ',' + stationName + ',' + -(shortestDistance * 1000));
            // 파일 경로 및 이름 설정
            
            // System.out.println(filePath);

            try {
                // CSV 형식으로 데이터를 문자열로 만듦
                String csvData = road_address_name.get(i).toString() + "," + address + "," + lines + "," + stationName + "," + xyList.get(i).getX() + "," + xyList.get(i).getY() + "," + -(shortestDistance * 1000);

                // 파일에 데이터 쓰기
                writer.write(csvData);
                writer.write("\n"); // 개행 문자 삽입 (데이터 라인 끝)

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("CSV 파일 생성 중 오류가 발생했습니다.");
            }
        }
        System.out.println("CSV 파일이 성공적으로 생성되었습니다.");
        // 파일 닫기
        writer.close();

        return xyList;
    }
}
