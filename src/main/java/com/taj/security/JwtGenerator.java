package com.taj.security;

import com.taj.model.RegistrationModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {


    public String generate(RegistrationModel jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getRegisteration_username());
        claims.put("userId", String.valueOf(jwtUser.getRegistration_id()));
        claims.put("role", jwtUser.getRegistration_role());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
