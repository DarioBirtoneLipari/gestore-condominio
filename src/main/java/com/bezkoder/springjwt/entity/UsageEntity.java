package com.bezkoder.springjwt.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="usageHouse")
public class UsageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column
	private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "house")
    private HouseEntity house;

    @Column
    private Date date;

    @Column
    private float water;

    @Column
    private float gas;
}
