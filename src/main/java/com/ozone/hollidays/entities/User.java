package com.ozone.hollidays.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozone.hollidays.enums.Gender;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.*;

@Data
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

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


    public User() {
    }

    public User(String userName, String email, String password, Gender sex, String profilePic, Boolean enabled, Collection<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.profilePic = profilePic;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Gender getSex() {
        return sex;
    }

    public String getProfilePic() {
        return profilePic;
    }
    @JsonIgnore
    public Boolean getEnabled() {
        return enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}