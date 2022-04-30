package com.ozone.hollidays.services.interfaces;

import com.ozone.hollidays.dtos.AuthenticationResponse;
import com.ozone.hollidays.dtos.LoginRequest;
import com.ozone.hollidays.dtos.RegisterRequest;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.entities.VerificationToken;

public interface AuthService {

    public AuthenticationResponse login(LoginRequest loginRequest);
    public void addRoleToUser(String username, String roleName);
    void fetchUserAndEnable(VerificationToken verificationToken);
    public void signup(RegisterRequest registerRequest);
    public  User  getCurrentUser();
}
