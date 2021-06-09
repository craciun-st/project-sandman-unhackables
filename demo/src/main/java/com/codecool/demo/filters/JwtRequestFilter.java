package com.codecool.demo.filters;

import com.codecool.demo.services.JwtUtil;
import com.codecool.demo.services.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component  // needed! filter is autowired in WebSecurityConfig class
public class JwtRequestFilter extends OncePerRequestFilter {

    private OurUserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    @Autowired
    public JwtRequestFilter(OurUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Look at the HTTP header of the request to get the JWT
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // The sought-header should be like // Authorization: "Bearer "+JWT //
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        // if a username can be extracted from the JWT and the client is not yet authenticated for the current
        // SecurityContext then:
        // 1. load the username (as transmitted in the JWT) from the repo via the service
        // 2. if the token is valid for those UserDetails create a new UsernamePasswordAuthenticationToken
        //  - with those UserDetails (name+password)
        //  - no specific credentials
        //  - a list of authorities (from UserDetails, in turn obtained from the repo)
        // 3. set that UPAToken with the details from the request
        // 4. set the SecurityContext to be authenticated via this UPAToken
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails;
            try {
                userDetails = this.userDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException err) {
                logger.error(err);
                userDetails = null;
            }

            if (userDetails != null && jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken
                        .setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // ...proceed with filtering
        filterChain.doFilter(request, response);
    }
}
