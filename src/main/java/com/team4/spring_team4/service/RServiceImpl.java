package com.team4.spring_team4.service;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class RServiceImpl implements RService{
    public String rPredict(double busStations, double distance, double leaseableArea, double floor, double yoc, double contractDate, double baseRate, double x, double y, String size, String isSale) {
        try {
            RConnection conn = new RConnection();
            // String rdsPath = getClass().getResource("/rds/" + size + ".rds").getPath();

            Resource rdsResource = new ClassPathResource("rds/" + size + ".rds");
            String rdsPath = rdsResource.getFile().getAbsolutePath();
            System.out.println(rdsPath);

            conn.voidEval("library(randomForest)");
            conn.voidEval("around10_rf <- readRDS('" + rdsPath +"')");
            

            String predictionResult = "";
            // isSale 0 -> 전세가, isSale 1 -> 매매가
            if(isSale.equals(0)){
                conn.voidEval("result <- predict(around10_rf, list(주변정류장개수=" + busStations + ", 역거리= " + distance +
                        ", 경도 = " + y + ", 위도 = " + x + ",임대면적=" + leaseableArea + " ,층= " + floor +
                        ",건축년도=" + yoc + ",계약시점=" + contractDate + ",계약시점기준금리=" + baseRate + "))");
                predictionResult = conn.eval("result").asString();
            }else{
                conn.voidEval("result <- predict(around10_rf, list(정류장수=" + busStations + ", 역간거리= " + distance + ", 지수=102.9551" +
                        ",전용면적=" + leaseableArea + " ,층= " + floor + ",건축년도=" + yoc + ",계약년월=" + contractDate + ",금리=" + baseRate + "))");
                // 3.3000 ~ 4.6000
                String result = conn.eval("result").asString();
                predictionResult = result + " 단위(억)";
            }

            
            conn.close();

            return predictionResult;
        } catch (Exception e) {
            e.printStackTrace();
            return "R Error";
        }
    }
}
