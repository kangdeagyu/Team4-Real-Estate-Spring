package com.team4.spring_team4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    
    int node_id;
    int ars_id;
    String name;
    double x;
    double y;
    String type; // 중앙? 일반 차로
    
}
