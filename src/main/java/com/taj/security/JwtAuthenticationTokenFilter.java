package com.taj.security;

import com.taj.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/evvaz/**");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type, if-none-match");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        String header = httpServletRequest.getHeader("Authorisation");


        if (header == null || !header.startsWith("Token=")) {
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "content-type, if-none-match");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(request, response);
    }
}
