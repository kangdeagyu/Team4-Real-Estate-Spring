package com.team4.spring_team4.service;

public interface RService{
    public String rPredict(double busStations, double distance, double leaseableArea, double floor, double yoc, double contractDate, double baseRate, double x, double y, String size, String isSale) throws Exception;
}