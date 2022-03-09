package com.ozone.hollidays.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private String expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}