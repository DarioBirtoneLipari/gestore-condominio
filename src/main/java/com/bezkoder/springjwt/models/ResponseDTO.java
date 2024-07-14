package com.bezkoder.springjwt.models;

import lombok.Data;

@Data
public class ResponseDTO {

    private String type;
    private String HTTPstatus;
    private String message;
    private Object o;
    
}
