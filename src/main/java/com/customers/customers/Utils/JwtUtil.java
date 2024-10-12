package com.customers.customers.Utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.customers.customers.Entities.User;

public class JwtUtil {

    private static final String SECRET_KEY = "asdqweqgh345v";

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);


    public static String generateToken(User user) {
        
        String token = JWT.create().withIssuer("auth0").
        withClaim("userId", user.getId()).
        withIssuedAt(new Date()).
        withExpiresAt(getExpirationDate()).
        sign(algorithm);

        return token;
    }
     
    private static Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7); // 7 dias de expiracion
    
    }

    public static String validateToken(String token) {
    
        JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer("auth0")
        .build();

        DecodedJWT decoded = verifier.verify(token);
        String userId = decoded.getClaim("userId").asString();
        return userId;
    }
}
