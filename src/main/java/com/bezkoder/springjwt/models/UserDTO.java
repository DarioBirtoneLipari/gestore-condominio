package com.bezkoder.springjwt.models;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
 

	private String id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String cell; //nome diverso da progettazione
	private Date birthDate;
	private String profileImg; //nome diverso da progettazione
	private String creditCard;
	private int cvv;
	private Date expire;
	private String holder;
	private String username;
	private Set<RoleDTO> roles;
	   
}
