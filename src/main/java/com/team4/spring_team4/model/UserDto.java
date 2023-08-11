package com.team4.spring_team4.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    
    String userid;
    String password;
    String address;
    String phone;
    int isdelete;

}
