package com.ozone.hollidays.entities;

import com.ozone.hollidays.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "sex")
    private Gender sex;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "enabled")
    private Boolean enabled;


}