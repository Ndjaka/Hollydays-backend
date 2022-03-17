package com.ozone.hollidays.dtos;

import com.ozone.hollidays.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String authentication;
    private String string;

}
