package com.bezkoder.springjwt.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="house")
public class HouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;


    @Column
    private String scala;

    @Column
    private int piano;

    @Column
    private int interno;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="user_id")
    private UserEntity user;

    @Column
    private String houseImg;

}


