package com.bezkoder.springjwt.models;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationDTO {

    
    private String id;
    private Date date;
    private String text;
    private UserDTO user;

}
