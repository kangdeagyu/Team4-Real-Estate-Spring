package com.team4.spring_team4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class subwayDto {
    
    int id;
    String name;
    String line;
    double x;
    double y;
    
}
