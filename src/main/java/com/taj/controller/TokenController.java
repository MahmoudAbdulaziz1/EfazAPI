package com.taj.controller;

import com.taj.model.NewLoginModelDto;
import com.taj.security.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {


    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/")
    public String generate(@RequestBody final NewLoginModelDto jwtUser) {

        return jwtGenerator.generate(jwtUser);

    }
}
