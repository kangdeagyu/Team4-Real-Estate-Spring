package com.team4.spring_team4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubwayDto {
    
    int id;
    String name;
    String line;
    double x;
    double y;
    
}
