package com.bezkoder.springjwt.models;

import java.util.Date;

import lombok.Data;


@Data
public class UsageDTO {

    private String id;
    private HouseDTO house;
    private Date date;
    private float water;
    private float gas;
    
}
