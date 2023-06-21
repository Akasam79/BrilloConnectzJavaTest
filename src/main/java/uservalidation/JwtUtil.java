package uservalidation;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String JWT_SECRET = "LEyA93gOtdnYGpKEdCVm0VWyLveYKTqA3m70vCq5DzVokrkOvEzVlyr94ddhddv";

    public static String generateJWT(String username) {
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiration = new Date(nowMillis + 36000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean verifyJWT(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
