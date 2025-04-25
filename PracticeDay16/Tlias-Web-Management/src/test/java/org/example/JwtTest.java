package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成jwt令牌
     * */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("username", "itheima");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRjYXN0")
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }

    /**
     * 解析jwt令牌
     * */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoiaXRoZWltYSIsImV4cCI6MTc0NTUxNDg2Nn0.7egehHrS8m8PctFLZNNDIEoMZU9h4X8cawJceubWNiw";
        Claims claims = Jwts.parser().setSigningKey("aXRjYXN0")//解析令牌的时候需要指定密钥，该密钥必须和生成的时候密钥完全一致
                .parseClaimsJws("token")//解析令牌
                .getBody();//获取令牌中的数据
        System.out.println(claims);
    }
}
