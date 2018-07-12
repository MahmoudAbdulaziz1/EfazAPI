package com.taj.security;

import com.taj.model.RegistrationModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "youtube";

    public RegistrationModel validate(String token) {

        RegistrationModel jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new RegistrationModel();

            jwtUser.setRegisteration_username(body.getSubject());
            jwtUser.setRegistration_id(Integer.parseInt((String) body.get("userId")));
            jwtUser.setRegistration_role((String) body.get("role"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
