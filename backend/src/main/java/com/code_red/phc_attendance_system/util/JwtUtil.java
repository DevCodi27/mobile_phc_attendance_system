package com.code_red.phc_attendance_system.util;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.code_red.phc_attendance_system.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String SECRET_KEY = "your_secret_key_your_secret_key_your_secret"; // Must be at least 32 bytes

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String generateToken(UserDetails userDetails, Set<Role> roles) {
		String roleNames = roles.stream().map(Role::getName).collect(Collectors.joining(","));

		return Jwts.builder().subject(userDetails.getUsername()).claim("roles", roleNames).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
				.signWith(getSigningKey()) // Corrected signing method
				.compact();
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	public List<String> extractRoles(String token) {
		Claims claims = extractClaims(token);
		return Arrays.asList(claims.get("roles", String.class).split(","));
	}

}
