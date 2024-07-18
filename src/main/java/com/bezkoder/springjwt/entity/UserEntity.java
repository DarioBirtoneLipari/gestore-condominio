package com.bezkoder.springjwt.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Size(max = 20)
  private String username;

  @Size(max = 50)
  private String email;

  @Size(max = 120)
  private String password;

  @Column
	private String name;

	@Column
	private String surname;

	@Column
	private String cell; //nome diverso da progettazione
	
	@Column
	private Date birthDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<HouseEntity> houses;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<NotificationEntity> notifications;

	@Column
	private String profileImg; //nome diverso da progettazione

	@Column
	private String creditCard;

	@Column
	private int cvv;

	@Column
	private Date expire;

	@Column
	private String holder;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public UserEntity(String id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
      @NotBlank @Size(max = 120) String password, String name, String surname, String cell, Date birthDate,
      List<HouseEntity> houses, String profileImg, String creditCard, int cvv, Date expire, String holder) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.cell = cell;
    this.birthDate = birthDate;
    this.houses = houses;
    this.profileImg = profileImg;
    this.creditCard = creditCard;
    this.cvv = cvv;
    this.expire = expire;
    this.holder = holder;
  }

  public UserEntity(){
    
  }

public UserEntity(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
        @NotBlank @Size(max = 120) String password, String name, String surname, String cell, Date birthDate,
        String profileImg, String creditCard, int cvv,
        Date expire, String holder) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.cell = cell;
    this.birthDate = birthDate;
    this.profileImg = profileImg;
    this.creditCard = creditCard;
    this.cvv = cvv;
    this.expire = expire;
    this.holder = holder;
}

 

}
