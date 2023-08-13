package com.team4.spring_team4.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ApiController {

    @RequestMapping("/getPrice")
    @ResponseBody
    public String getPrice(HttpServletRequest request) throws Exception {

        // // 지번 데이터 받아왔을 때 글자 제거 후 뒤에 지번만 검색해야 api 이용 가능하기 때문에 불필요한 부분 제거
        String address = request.getParameter("address");
        String floor = request.getParameter("floor");
        // String address = "서울특별시 강남구 역삼동 755-4";
        // System.out.println("지번 : " + address.substring(14));

        // -------------------
        String[] date = {
                "202108", "202109", "202110", "202111", "202112",
                "202201", "202202", "202203", "202204", "202205", "202206", "202207",
                "202208", "202209", "202210", "202211", "202212",
                "202301", "202302", "202303", "202304", "202305", "202306", "202307"
        }; // 2년치 년월 배열 생성
        int index = 0; // 배열 인덱스
        JSONArray apartmentDataArray = new JSONArray();
        JSONObject jsonList = new JSONObject(); // object로 변환하기 위한 객체 생성

        StringBuilder urlBuilder = new StringBuilder(
                "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                + "=NrexXq58W%2BTYnmrniz9qD3V1EMI7C8xsbReiSlQl%2B3hsThhcONRGUd4UOvDVvqGqISojBUte%2BixcSB6EdwXWzw%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode("11680", "UTF-8")); // 강남구
                                                                                                                    // 법정동코드
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(date[index], "UTF-8")); // 연,월
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        conn.connect();

        // XML 응답 파싱
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(conn.getInputStream());

        Element root = document.getRootElement();
        Element body = root.getChild("body");
        Element items = body.getChild("items");
        List<Element> item = items.getChildren("item");

        int count = 0; // 조회된 데이터가 하나도 없는지 파악하기 위한 변수 초기화

        for (int i = 0; i < date.length; i++) { // 2년치 월별 검색 반복
            JSONArray apartmentMonthlyDataArray = new JSONArray(); // 월별 데이터를 담을 배열

            for (Element element : item) {
                if (element.getChildText("지번").equals(address) &&
                        element.getChildText("월세금액").equals("0") &&
                        Integer.parseInt(element.getChildText("층")) == Integer.parseInt(floor)) 
                    {
                        JSONObject apartmentMonthlyData = new JSONObject();
                        apartmentMonthlyData.put("아파트명", element.getChildText("아파트"));
                        apartmentMonthlyData.put("보증금액", Integer.parseInt(element.getChildText("보증금액").replace(",", "")));
                        apartmentMonthlyDataArray.put(apartmentMonthlyData);
                        count++; // 
                }
            }

            JSONObject apartmentData = new JSONObject();
            apartmentData.put(date[i], apartmentMonthlyDataArray);
            apartmentDataArray.put(apartmentData);

            index++;
        }

        conn.disconnect();

        // JSONArray를 Object 형식으로 감싸기
        jsonList.put("results",
        (address != null /* && floor != null*/)
            ? (count > 0 ? apartmentDataArray : "EMPTY")
            : "ERROR"); // 필수조건이 null일 때 ERROR, 조회된 데이터가 없을 때 EMPTY 출력
        //System.out.println(jsonList);

        return jsonList.toString();
    }

}
