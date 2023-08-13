package com.team4.spring_team4.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

@Service
public class PredictLeaseServiceImpl implements PredictLeaseService {
    public String predictLease(double busStations, double distance, double leaseableArea, double floor, double yoc, double contractDate, double baseRate, double x, double y, String model) {
        String result;
        double[] coefficients = new double[10];
        double error = 0;

        // 모델 예측값 계산
        double predictedValue = 0.0;
		try {
            if(model == "under10"){
                // 모델 계수
                coefficients = new double[] { -40978682.70752, 350.91676, 651732.72785, -1199201.83843, 6.12483,
                        1145.71399, 230.20109, 765.77415, 0.07986, -919.41683 };
                // 오차 범위
                error = 4742;
            } else if(model == "around10") {
                coefficients = new double[] { -124466084.81976, 1576.13488, 1192079.06839, -968925.63909, 14.31228,
					1569.01268, 225.75821, 1156.22681, 0.34696, -5059.48636 };
                error = 13560;
            } else if(model == "around20") {
                coefficients = new double[] { -351067821.50131, 1935.94293, 2654105.00709, -99332.35518, 5.34265,
					1249.41980, 314.19017, 2154.46171, 0.65660, -6907.54891 };
                error = 16650;
            } else if(model == "around30") {
                coefficients = new double[] { -360290218.72461, 2873.61582, 2776722.27235, -501428.51329, -27.41421,
					1158.78563, 269.95696, 3826.42969, 0.92080, -7218.02783 };
                error = 24300;
            } else {
                coefficients = new double[] {
                    -131660673.88709, 316.83480, 916854.16575, 153205.94080, -12.39590,
                    93.91930, 159.85300, 1273.18969, 0.34349, -4385.35705,
                    33474.13111, 62405.99192, 102561.52159, 129424.76236
                };
                error = 23010;
            }

			// 입력 변수
			double[] input = { 1, busStations, y, x, distance, leaseableArea, floor, yoc, contractDate, baseRate };

			for (int i = 0; i < coefficients.length; i++) {
				predictedValue += coefficients[i] * input[i];
			}

			// 오차 범위 계산
			double lowerBound = predictedValue - error;
			double upperBound = predictedValue + error;

			// 오차 범위 포맷팅을 위한 DecimalFormat 생성
			DecimalFormat formatter = new DecimalFormat("#.###");

			// 만원 단위를 억 단위로 변환하여 포맷팅
			String formattedLowerBound = formatter.format(lowerBound / 10000); // 만원을 억으로 변환
			String formattedUpperBound = formatter.format(upperBound / 10000); // 만원을 억으로 변환

			// 결과 문자열 생성
			result = formattedLowerBound + " 억 ~ " + formattedUpperBound + " 억";

		} catch (Exception e) {
			e.printStackTrace();
			result = "Error occurred during prediction.";
		}
		return result;
    }
}
