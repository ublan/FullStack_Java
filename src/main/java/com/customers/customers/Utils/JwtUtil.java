package com.customers.customers.Utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.customers.customers.Entities.User;

public class JwtUtil {

    private static final String SECRET_KEY = "3lB4ut4!";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public static String generateToken(User user) {
        String token = JWT.create()
                .withIssuer("ATLAcademy")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(getExpiresDate())
                .sign(algorithm);
        return token;
    }

    public static String getUserIdByToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("ATLAcademy")
                .build();

        DecodedJWT decoded = verifier.verify(token);
        String userId = decoded.getClaim("userId").toString();
        return userId;
    }

    private static Date getExpiresDate() {
        return new Date(System.currentTimeMillis()
                + (1000L * 60 * 60 * 24 * 14)); // 14 days
    }
}
