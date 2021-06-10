package com.codecool.demo.config;

import com.codecool.demo.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService ourUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(UserDetailsService ourUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.ourUserDetailsService = ourUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    // need to set the AuthenticationManager to use ourUserDetailsService
    // and the DAO auth mechanism
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
//        auth.userDetailsService(ourUserDetailsService);
    }

    // the AuthenticationManager configured in this class's configure() method is not in the global scope;
    // the Bean below (the bean obtained via the superclass method), however, IS GLOBAL!
    // it is more of a hack, so that the configureGlobal autowiring above would make sense
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // need to set the AuthenticationManager to use ourUserDetailsService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ourUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        int strength = 10;  // 2^strength iterations (supposedly, the nr. of XOR filters in the algorithm)
                            // (10 is sufficiently hard for current hardware to brute-force)
        return new BCryptPasswordEncoder(strength, new SecureRandom()); // use a cryptographically secure random nr.
                                                                        // as the salt

//        return NoOpPasswordEncoder.getInstance();     // to test against errors due to wrong wiring of encoding
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests(
                        (authCustomizer) -> authCustomizer
                            .antMatchers("/auth").permitAll()
                            .anyRequest().authenticated()
                );
        http.exceptionHandling()
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Have the JwtRequestFilter be applied before standard User & Password authentication
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
