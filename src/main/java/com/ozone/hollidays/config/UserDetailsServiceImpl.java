package com.ozone.hollidays.config;

import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG =    "user with email %s not found";

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional =  userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));

        return new org.springframework
                .security
                .core
                .userdetails
                .User(
                user.getUserName(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                 true,
                getAuthorities("USER")
               );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
