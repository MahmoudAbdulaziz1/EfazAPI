package com.taj.config;

import com.taj.security.JwtAuthenticationEntryPoint;
import com.taj.security.JwtAuthenticationProvider;
import com.taj.security.JwtAuthenticationTokenFilter;
import com.taj.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }




    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/evvaz/**").authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/evvaz/companyOffer/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/cat/**").authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/evvaz/companyOffer/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/response/school/request/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/seen/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/details/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/admin/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/profile/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/enquiry/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/enquiry/response/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/school/profile/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/receive/place/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/school/category/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/school/request/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/school/request/offer/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/offer/seen/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/takataf/category/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/takataf/first/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/tender/seen/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/takataf/second/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/takataf/third/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/takataf/tender/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/evvaz/tender/request/**").authenticated()
                .and()
//                .authorizeRequests().antMatchers("/token/**").authenticated()
//                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }


}
