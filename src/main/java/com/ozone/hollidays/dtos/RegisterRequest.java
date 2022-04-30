package com.ozone.hollidays.dtos;

import com.ozone.hollidays.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    private Gender sex;
}
