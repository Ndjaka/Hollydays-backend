package com.ozone.hollidays.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "enabled")
    private Boolean enabled;



    public User(String name, String surName, String email, String password, Boolean sex, String profilePic, Boolean enabled) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.profilePic = profilePic;
        this.enabled = enabled;
    }
}