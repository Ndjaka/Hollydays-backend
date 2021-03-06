package com.ozone.hollidays.entities;

import com.ozone.hollidays.enums.StatusType;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

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
    private Instant date;

    @Column(name = "status")
    private StatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}