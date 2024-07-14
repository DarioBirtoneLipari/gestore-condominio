package com.bezkoder.springjwt.models;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDTO {
    
    private String id;
    private boolean isPaid;
    private boolean ongoing;
    private Date startDate;
    private Date paymentDate;
    private UserDTO user;
    private HouseDTO house;
    private String description;
    private String total;
    private String creditCard;
}
