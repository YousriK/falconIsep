package com.falcon.avisep.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class TokenUtils
{
    private final Logger LOG = LoggerFactory.getLogger(TokenUtils.class);

    private final String SUB = "sub";
    private final String AUTHORITIES = "role";
    private final String PASSWORD = "password";
    private final String CREATED = "created";
    private final String EXPIRED = "expired";

    public long expiration=3600;

    public String secret="simao";

    public String generateToken(String login,String password,String authorities)
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put(SUB,login);
        claims.put(PASSWORD,password); // Save Original Password. No Hash Password
        claims.put(AUTHORITIES,authorities);
        LocalDateTime now = LocalDateTime.now();
        claims.put(CREATED,now);
        claims.put(EXPIRED,now.plusHours(1));
        return encoded(claims);
    }

    public String refreshToken(String token)
    {
        Claims claims = getClaims(token);
        LocalDateTime now = LocalDateTime.now();
        claims.put(CREATED,now);
        claims.put(EXPIRED,now.plusHours(1));
        return encoded(claims);
    }


    public boolean exist(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims != null;
    }

    public boolean isExpiate(String token)
    {
        Claims claims = getClaims(token);
        LocalDateTime expiateDate = (LocalDateTime) claims.get(EXPIRED);
        return LocalDateTime.now().isAfter(expiateDate);
    }

    public List<GrantedAuthority> getAuthorityInToken(String token)
    {
        Claims claims = getClaims(token);
        String authorities = (String) claims.get(AUTHORITIES);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    public String getLoginInToken(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(SUB);
    }

    public String getPasswordInToken(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(PASSWORD);
    }

    private String encoded(Map<String, Object> claims)
    {
        String token =  Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.ES256,secret)
                .compact();
        LOG.info("New token is create");
        return token;
    }
    private Claims getClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody();
    }
}
