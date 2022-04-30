package com.ozone.hollidays.dtos;

import com.ozone.hollidays.entities.Role;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String access_token;
    private String userName;
    private String email;
    private String sex;
    private String profilePic;
    private Collection<Role> roles;


}
