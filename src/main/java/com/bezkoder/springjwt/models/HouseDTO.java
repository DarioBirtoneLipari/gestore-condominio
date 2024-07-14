package com.bezkoder.springjwt.models;


import lombok.Data;

@Data
public class HouseDTO {
   
    private String id;
    private String scala;
    private int interno;
    private int piano;
    private UserDTO user;
    private String houseImg;


    
}
