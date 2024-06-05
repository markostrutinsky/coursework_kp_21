package PawnShop.PawnShop.security.jwt;

import PawnShop.PawnShop.model.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtService {

//    private final JwtDeserializer jwtDeserializer;

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
    public static final String SCOPE_CLAIM = "scope";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractScope(String token) {
        return extractClaim(token, SCOPE_CLAIM);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        String scope = claims.get(claimName, String.class);
        System.out.println("Scope is " + scope);
        return scope;
    }

    private Claims extractAllClaims(String token) {
        token = token.startsWith("Bearer ")
                ? token.replaceAll("Bearer ", "")
                : token;
        System.out.println(token);
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token) {
        final String username = extractUsername(token);
        System.out.println("Username is " + username);
        return (!isTokenExpired(token));
    }

    public String generateToken(User user) {
        String token = Jwts.builder()
                .signWith(getSignKey())
                .claims()
                .expiration(Date.from((Instant.now().plus(2, ChronoUnit.HOURS))))
                .subject(user.getUsername())
                .add(SCOPE_CLAIM, user.getAuthority().getGranted())
                .and()
                .compact();

        System.out.println("Token is: " + token);

        return token;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
