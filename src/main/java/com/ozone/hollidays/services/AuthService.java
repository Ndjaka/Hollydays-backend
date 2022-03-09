package com.ozone.hollidays.services;

import com.ozone.hollidays.entities.NotificationEmail;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.entities.VerificationToken;
import com.ozone.hollidays.exception.HollydaysException;
import com.ozone.hollidays.repositories.UserRepository;
import com.ozone.hollidays.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(User registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        user.setSurName(registerRequest.getSurName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setSex(registerRequest.getSex());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(),
                "Thank you for signing up to Hollydays, please click on the below url to activate your account :" +
                        " http://localhost:8080/api/v1/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
      Optional<VerificationToken> verificationToken =  verificationTokenRepository.findByToken(token);
      verificationToken.orElseThrow(()-> new HollydaysException("Invalid Token"));
      fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
       User user =  userRepository.findByEmail(email).orElseThrow(()-> new HollydaysException("User not found"));
       user.setEnabled(true);
       userRepository.save(user);
    }
}
