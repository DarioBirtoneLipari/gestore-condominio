package com.bezkoder.springjwt.models;

import java.util.List;

import lombok.Data;

@Data
public class ResponseListDTO {
    private String type;
    private String HTTPstatus;
    private String message;
    private List<Object> lo;
    
}
