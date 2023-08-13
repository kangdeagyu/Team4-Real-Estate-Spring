package com.team4.spring_team4.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.team4.spring_team4.service.PredictLeaseService;

@RestController
public class PredictLeaseController {

	@Autowired
	PredictLeaseService leaseService;
	
	@RequestMapping("/predictLease")
	// 사용 예시 : http://localhost:8080/predictLease?busStations=7.0&distance=-311.6476928953675&leaseableArea=120&floor=1.0&yoc=2001.0&contractDate=20220816.0&baseRate=2.25&x=37.50788140290944&y=127.03732073307039&model=around10
	public String predictLease(
			@RequestParam double busStations,
			@RequestParam double y,
			@RequestParam double x,
			@RequestParam double distance,
			@RequestParam double leaseableArea,
			@RequestParam double floor,
			@RequestParam double yoc,
			@RequestParam double contractDate,
			@RequestParam double baseRate,
			@RequestParam String model
	) {
		try {
			String result = leaseService.predictLease(busStations, distance, leaseableArea, floor, yoc, contractDate, baseRate, x, y, model);
			return "{\"result\":\"" + result + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"error\"}";
		}
	}
	

	@RequestMapping("/predict_under10")
	public String predictLeaseUnder10(
			@RequestParam double busStations,
			@RequestParam double y,
			@RequestParam double x,
			@RequestParam double distance,
			@RequestParam double leaseableArea,
			@RequestParam double floor,
			@RequestParam double yoc,
			@RequestParam double contractDate,
			@RequestParam double baseRate) {
		String result;
		try {
			// 모델 계수
			double[] coefficients = { -40978682.70752, 350.91676, 651732.72785, -1199201.83843, 6.12483,
					1145.71399, 230.20109, 765.77415, 0.07986, -919.41683 };

			// 입력 변수
			double[] input = { 1, busStations, y, x, distance, leaseableArea, floor, yoc, contractDate, baseRate };

			// 모델 예측값 계산
			double predictedValue = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				predictedValue += coefficients[i] * input[i];
			}

			// 오차 범위 계산
			double lowerBound = predictedValue - 4742;
			double upperBound = predictedValue + 4742;

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

	@RequestMapping("/predict_around10")
	public String predictLeaseAround10(
			@RequestParam double busStations,
			@RequestParam double y,
			@RequestParam double x,
			@RequestParam double distance,
			@RequestParam double leaseableArea,
			@RequestParam double floor,
			@RequestParam double yoc,
			@RequestParam double contractDate,
			@RequestParam double baseRate
	// http://localhost:8080/predict_around10?busStations=7.0&distance=-311.6476928953675&leaseableArea=66.34&floor=1.0&yoc=2001.0&contractDate=20220816.0&baseRate=2.25&x=37.50788140290944&y=127.03732073307039
	) {
		String result;
		try {
			// 모델 계수
			double[] coefficients = { -124466084.81976, 1576.13488, 1192079.06839, -968925.63909, 14.31228,
					1569.01268, 225.75821, 1156.22681, 0.34696, -5059.48636 };

			// 입력 변수
			double[] input = { 1, busStations, y, x, distance, leaseableArea, floor, yoc, contractDate, baseRate };

			// 모델 예측값 계산
			double predictedValue = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				predictedValue += coefficients[i] * input[i];
			}

			// 오차 범위 계산
			double lowerBound = predictedValue - 13560;
			double upperBound = predictedValue + 13560;

			// 오차 범위 포맷팅을 위한 DecimalFormat 생성
			DecimalFormat formatter = new DecimalFormat("#.####");

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

	@RequestMapping("/predict_around20")
	public String predictLeaseAround20(
			@RequestParam double busStations,
			@RequestParam double y,
			@RequestParam double x,
			@RequestParam double distance,
			@RequestParam double leaseableArea,
			@RequestParam double floor,
			@RequestParam double yoc,
			@RequestParam double contractDate,
			@RequestParam double baseRate) {
		String result;
		try {
			// 모델 계수
			double[] coefficients = { -351067821.50131, 1935.94293, 2654105.00709, -99332.35518, 5.34265,
					1249.41980, 314.19017, 2154.46171, 0.65660, -6907.54891 };

			// 입력 변수
			double[] input = { 1, busStations, y, x, distance, leaseableArea, floor, yoc, contractDate, baseRate };

			// 모델 예측값 계산
			double predictedValue = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				predictedValue += coefficients[i] * input[i];
			}

			// 오차 범위 계산
			double lowerBound = predictedValue - 16650;
			double upperBound = predictedValue + 16650;

			// 오차 범위 포맷팅을 위한 DecimalFormat 생성
			DecimalFormat formatter = new DecimalFormat("#.####");

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

	@RequestMapping("/predict_around30")
	public String predictLeaseAround30(
			@RequestParam double busStations,
			@RequestParam double y,
			@RequestParam double x,
			@RequestParam double distance,
			@RequestParam double leaseableArea,
			@RequestParam double floor,
			@RequestParam double yoc,
			@RequestParam double contractDate,
			@RequestParam double baseRate) {
		String result;
		try {
			// 모델 계수
			double[] coefficients = { -360290218.72461, 2873.61582, 2776722.27235, -501428.51329, -27.41421,
					1158.78563, 269.95696, 3826.42969, 0.92080, -7218.02783 };

			// 입력 변수
			double[] input = { 1, busStations, y, x, distance, leaseableArea, floor, yoc, contractDate, baseRate };

			// 모델 예측값 계산
			double predictedValue = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				predictedValue += coefficients[i] * input[i];
			}

			// 오차 범위 계산
			double lowerBound = predictedValue - 24300;
			double upperBound = predictedValue + 24300;

			// 오차 범위 포맷팅을 위한 DecimalFormat 생성
			DecimalFormat formatter = new DecimalFormat("#.####");

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

	@RequestMapping("/predict_around40")
	public String predictLeaseAround40(
			@RequestParam double busStations,
			@RequestParam double y,
			@RequestParam double x,
			@RequestParam double distance,
			@RequestParam double leaseableArea,
			@RequestParam double floor,
			@RequestParam double yoc,
			@RequestParam double contractDate,
			@RequestParam double baseRate) {
		String result;
		try {
			// 모델 계수
			double[] coefficients = { -809042452.57753, -1812.42085, 5694559.60587, 1712385.81817, -98.99864,
					12.16510, 263.58886, 792.42471, 0.98412, -9473.00263 };

			// 입력 변수
			double[] input = { 1, busStations, y, x, distance, leaseableArea, floor, yoc, contractDate, baseRate };

			// 모델 예측값 계산
			double predictedValue = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				predictedValue += coefficients[i] * input[i];
			}

			// 오차 범위 계산
			double lowerBound = predictedValue - 23010; // 오차 범위는 예시 값 그대로 사용
			double upperBound = predictedValue + 23010;

			// 오차 범위 포맷팅을 위한 DecimalFormat 생성
			DecimalFormat formatter = new DecimalFormat("#.####");

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

} // End Class
