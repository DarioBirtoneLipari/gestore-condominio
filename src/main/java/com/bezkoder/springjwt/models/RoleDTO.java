package com.bezkoder.springjwt.models;

import com.bezkoder.springjwt.entity.ERole;

import lombok.Data;

@Data
public class RoleDTO {

    private Integer id;
    private ERole name;
    
}
