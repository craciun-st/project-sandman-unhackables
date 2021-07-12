package com.codecool.demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

//    @Value("${jwt.secret}")
    private String secretKey = "THIS SHOULD BE AN ENV VARIABLE AND NOT SET HERE!";

    public static final String ISSUER_URI = "ProjSandman@localhost";

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        // since new Date() gives us the current time, we want the expiration date
        // - the 'exp' key in the Claims (a.k.a payload) -
        // to be before said Date()
        return extractExpiration(token).before(  new Date() );
    }

    /**
     *  This is the main method by which we certify that a user says he is who he is through the JWT
     *  (so we need not query the DB, rather just verify that the security context - the UserDetails - for the
     *  client matches the value the client has in the token).
     *  A token must not be expired in order to be valid
     * @param token a String representing the JWToken
     * @param userDetails the UserDetails as implemented in the Spring Security context
     * @return true if the token is valid
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    private String createToken(Map<String, Object> claims, String username) {
        int hourInMillis = 1000 * 60 * 60;
        return Jwts.builder()
                .setClaims(claims)  // set payload
                .setSubject(username)   // set subject (who is the token about)
                                        // -- must be unique at least in local context!! (usernames must be unique)
                .setIssuer(ISSUER_URI)   // set issuer (the server) - usually an URI
                .setIssuedAt( new Date(System.currentTimeMillis()) )  // need to set this to Unix ms as Long
                .setExpiration( new Date(System.currentTimeMillis() + 7*hourInMillis) ) // expires after 7h
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    /**
     * Once a user has generated a UserDetails by sending credentials (initial login) the server must reply back
     * with a JWT corresponding to that UserDetails.<br>
     *
     * This is the method to call to populate the <tt>jwt</tt> field of
     * {@link com.codecool.demo.model.authentication.AuthResponse} <br>
     * Once that has been served, that's the end of the server's responsibility for JWT storage.
     * The client must store the JWT and send it in
     * headers for subsequent confirmation of authorization (see the <tt>validateToken</tt> method)
     * @param userDetails UserDetails obtained from a Service after initial login
     * @return a String representation of the value for the JWT
     */
    public String generateResponseJwt(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //
        // add attributes to claims if needed here (if we want to differentiate tokens more than just by username)
        //
        return createToken(claims, userDetails.getUsername());
    }

}
