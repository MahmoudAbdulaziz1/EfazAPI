package com.taj.security;

import com.taj.model.LoginModel;
import com.taj.model.RegistrationModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class JwtGenerator {


    public String generate(LoginModel jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUser_email());
        claims.put("userId", String.valueOf(jwtUser.getLogin_id()));
        claims.put("role", jwtUser.getLogin_role());
        claims.put("currentDate", new Timestamp(System.currentTimeMillis()).getTime());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
