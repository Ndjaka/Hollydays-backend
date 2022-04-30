package com.ozone.hollidays.services.userService;

import com.ozone.hollidays.dtos.AuthenticationResponse;
import com.ozone.hollidays.dtos.LoginRequest;
import com.ozone.hollidays.dtos.RegisterRequest;
import com.ozone.hollidays.entities.NotificationEmail;
import com.ozone.hollidays.entities.Role;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.entities.VerificationToken;
import com.ozone.hollidays.exception.HollydaysException;
import com.ozone.hollidays.repositories.RoleRepository;
import com.ozone.hollidays.repositories.UserRepository;
import com.ozone.hollidays.repositories.VerificationTokenRepository;
import com.ozone.hollidays.config.JwtProvider;
import com.ozone.hollidays.services.mailService.MailService;
import com.ozone.hollidays.services.interfaces.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @Transactional
    @Override
    public void signup(RegisterRequest registerRequest) {

        log.info("user :"+registerRequest);
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setSex(registerRequest.getSex());
        user.setEnabled(false);
        userRepository.save(user);
        addRoleToUser(registerRequest.getUserName(),"USER");

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(),
                "Thank you for signing up to Hollydays, please click on the below url to activate your account :" +
                        " http://localhost:8888/api/v1/auth/accountVerification/" + token));
    }

    @Override
    public User getCurrentUser() {
            String name = (String) JwtProvider.getJwtInfo().get("name");

        return userRepository.findByUserName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + name));
    }

    @Transactional
    @Override
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new HollydaysException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new HollydaysException("User not found"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse().builder()
                .profilePic(user.getProfilePic())
                .access_token(token)
                .sex(user.getSex().name())
                .email(user.getEmail())
                .userName(user.getUserName())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByUserName(username).get();
        user.getRoles().add(role);

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
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new HollydaysException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }

}
