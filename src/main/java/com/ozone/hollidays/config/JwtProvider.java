package com.ozone.hollidays.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class JwtProvider {



    public String generateToken(Authentication authentication) {
        User user=(User) authentication.getPrincipal();
        List<String> roles=new ArrayList<>();
        authentication.getAuthorities().forEach(a->{
            roles.add(a.getAuthority());
        });
        String jwt= JWT.create()
               //.withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityParams.SECRET));
        return  jwt;
    }

    private static String  getAuthorizationToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        return token;
    }

    public static Map getJwtInfo(){
        Map<String, String> info = new HashMap<>();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
        String jwt = getAuthorizationToken();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        info.put("name",username);
        info.put("roles", String.valueOf(roles));

        return info;
    }

}

