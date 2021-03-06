package ibf.paf.blogproject.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtProvider {

    private byte[] bytes = new byte[] {-1, -7, -5, -91, 66, -127, -98, 102, -22, -66, 29, 89, 125, -101, -9, 23, 34,
            -51, 83, 52, -22, 70, -128, 57, -1, -58, 15, 56, -51, 110, -124, 82, 99, -43, -72, 73, -91, -56, -69, 43,
            -70, 0, -67, -20, -51, -14, 117, -20, 0, -106, -54, 26, -77, 125, -85, 9, 126, 76, -23, 114, 32, 21, -97, 25};
    private Key key;

    @PostConstruct
    public void init() {
//        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        key = Keys.hmacShaKeyFor(bytes);
        }

    public String generateToken (Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

    public  boolean validateToken(String jwt) {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build().parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
