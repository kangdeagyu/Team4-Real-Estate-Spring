<%@page import="org.rosuda.REngine.Rserve.RConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	double busStations = Double.parseDouble(request.getParameter("busStations"));
	double distance = Double.parseDouble(request.getParameter("distance"));
	double leaseableArea  = Double.parseDouble(request.getParameter("leaseableArea"));
	double floor  = Double.parseDouble(request.getParameter("floor"));
    double yoc  = Double.parseDouble(request.getParameter("yoc"));
	double contractDate  = Double.parseDouble(request.getParameter("contractDate"));
	double baseRate = Double.parseDouble(request.getParameter("baseRate"));
	double x = Double.parseDouble(request.getParameter("x"));
	double y = Double.parseDouble(request.getParameter("y"));
	
	RConnection conn = new RConnection();
	
	conn.voidEval("library(randomForest)");

	//conn.voidEval("around10_rf <- readRDS(url('http://localhost:8080/Rserve/around10.rds','rb'))");
	conn.voidEval("around10_rf <- readRDS('classpath:rds/around10.rds')");

	conn.voidEval("result <- predict(around10_rf, list(주변정류장개수=" + busStations + ", 역거리= " + distance + ", 경도 = " + y + ", 위도 = " + x +
    ",임대면적=" + leaseableArea + " ,층= " + floor + ",건축년도=" + yoc + ",계약시점=" + contractDate + ",계약시점기준금리=" + baseRate + "))");

	String predictionResult = conn.eval("result").asString();
	System.out.println(predictionResult);

%>
{"result":"<%= predictionResult %>"}  
