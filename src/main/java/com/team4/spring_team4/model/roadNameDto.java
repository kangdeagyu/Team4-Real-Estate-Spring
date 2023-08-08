package com.team4.spring_team4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoadNameDto {
    String city;
    String gu;
    String roadName;
    String aptName = "";
}
