package com.example.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenExpirationMs}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refreshTokenExpirationMs}")
    private long refreshTokenExpirationMs;

    // 从令牌中提取用户名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 从令牌中提取用户ID
    public Long extractUserId(String token) throws SignatureException {
        Claims claims = extractAllClaims(token);
        return claims == null ? null : Long.parseLong(claims.get("userId").toString());
    }

    // 从令牌中提取过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取指定 Claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claims == null ? null : claimsResolver.apply(claims);
    }

    // 提取所有的 Claims
    private Claims extractAllClaims(String token) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(secret);
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (SignatureException ex) {
            return null;
        }

    }

    // 检查令牌是否过期
    private Boolean isTokenExpired(String token) {
        Date date = extractExpiration(token);
        return date != null && date.before(new Date());
    }

    // 生成访问令牌
    public String generateAccessToken(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); // Assuming CustomUserDetails contains userId
        return createToken(claims, username, accessTokenExpirationMs);
    }

    // 生成刷新令牌
    public String generateRefreshToken(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); // Assuming CustomUserDetails contains userId
        return createToken(claims, username, refreshTokenExpirationMs);
    }

    // 根据用户信息生成令牌
    private String createToken(Map<String, Object> claims, String subject, long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // 验证令牌
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (!CheckUtil.isEmpty(username) || username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String refreshAccessToken(String refreshToken) {

        String username = extractUsername(refreshToken);
        Long userId = extractUserId(refreshToken);
        return CheckUtil.isEmpty(userId) || CheckUtil.isEmpty(username) ? null : generateAccessToken(userId, username);
    }

}
