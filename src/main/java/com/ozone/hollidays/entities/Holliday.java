package com.ozone.hollidays.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hollidays")
public class Holliday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "hotel")
    private String hotel;

    @Column(name = "date", length = 25)
    private String date;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}