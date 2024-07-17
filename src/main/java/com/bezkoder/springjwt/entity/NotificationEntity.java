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
@Table(name="notification")
public class NotificationEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private Date date;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="user_id")
    private UserEntity user;

    
}
