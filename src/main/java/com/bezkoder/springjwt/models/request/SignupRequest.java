package com.bezkoder.springjwt.models.request;

import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private String name;
	private String surname;
	private String cell; //nome diverso da progettazione
	private Date birthDate;
	private String profileImg; //nome diverso da progettazione
	private String creditCard;
	private int cvv;
	private Date expire;
	private String holder;

  
}
