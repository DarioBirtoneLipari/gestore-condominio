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
@Table(name="payment")
public class PaymentEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column
	private String id;

    @Column
    private boolean isPaid;

    @Column
    private Date startDate;

    @Column 
    private Date paymentDate;

    @Column
    private boolean ongoing;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="house_id")
    private HouseEntity house;

    @Column
    private float total;

    @Column
    private String description;

    @Column
    private String creditCard;
 //joinabile ma non progettualizzao cosi' poiche' cc potrebbe non essere quella salvata dall'utente
 //quindi inutile


    
}
