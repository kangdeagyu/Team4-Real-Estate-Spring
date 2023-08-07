package com.team4.spring_team4.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Controller
public class apiController {

    @RequestMapping("/getPrice")
    public String getPrice() throws Exception {

        StringBuilder urlBuilder = new StringBuilder(
                "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent"); /*
                                                                                                                              * URL
                                                                                                                              */
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                + "=NrexXq58W%2BTYnmrniz9qD3V1EMI7C8xsbReiSlQl%2B3hsThhcONRGUd4UOvDVvqGqISojBUte%2BixcSB6EdwXWzw%3D%3D"); /*
                                                                                                                           * Service
                                                                                                                           * Key
                                                                                                                           */
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode("11680", "UTF-8")); // 역삼동
                                                                                                                    // 지역코드
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("202001", "UTF-8")); // 연,
                                                                                                                      // 월
                                                                                                                      // 선택
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();

        String xmlData = sb.toString();
        System.out.println(xmlData);

        // XML을 JSON으로 변환
        // XmlMapper xmlMapper = new XmlMapper();
        // ObjectMapper objectMapper = new ObjectMapper();
        // Object xmlObject = xmlMapper.readValue(xmlData, Object.class);
        // String jsonData = objectMapper.writeValueAsString(xmlObject);
        // System.out.println(jsonData);

        // JSON 데이터를 Map으로 변환

        return xmlData;
    }

}
