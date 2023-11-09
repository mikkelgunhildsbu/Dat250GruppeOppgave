package no.feedapp.group2.FeedApp.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private static SecretKey getKey() {
        Dotenv dotenv = Dotenv.load();
        var secret = dotenv.get("SECRET");
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public static String createToken(String email) {
        return Jwts.builder()
                .issuer("FeedApp")
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000))
                .issuedAt(new Date())
                .signWith(getKey()).compact();
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public static Boolean validToken(String token) {
        try {
            var jwt = Jwts.parser();
            jwt.verifyWith(getKey()).build().parseSignedClaims(token);
            jwt.requireAudience("FeedApp");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getEmail(String token) {
        var content = Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
        return content.getSubject();
    }
}
