package com.taj.security;

import com.taj.model.LoginModel;
import com.taj.model.RegistrationModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "youtube";

    public LoginModel validate(String token) {

        LoginModel jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new LoginModel();

            jwtUser.setUser_email(body.getSubject());
            jwtUser.setLogin_id(Integer.parseInt((String) body.get("userId")));
            jwtUser.setLogin_role((String) body.get("role"));
            jwtUser.setLogin_date((long) body.get("currentDate"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
