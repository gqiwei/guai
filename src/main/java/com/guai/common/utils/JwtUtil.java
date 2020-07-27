package com.guai.common.utils;

import com.guai.common.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * JWT工具类
 * @author gqw
 * @date 2020-07-18
 */
@Component
public class JwtUtil {

    @Value("${jwt.header}")
    private String header;//自定义标识
    @Value("${jwt.secret}")
    private String secret; //秘钥
    @Value("${jwt.expireTime}")
    private int expireTime; //有效期

    private static final String USERID = "userid";//用户id
    private static final String CREATED = "created";//创建时间
    private static final String USERNAME = "username";//用户账号
    private static final String AUTHORITIES = "authorities";//权限列表
    private static final String TOKEN_PREFIX = "Bearer "; //令牌前缀

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public SecurityUser getSecurityUser(HttpServletRequest request){
        String token = getToken(request);
        if(StringUtils.isNotEmpty(token)){
            Claims claims = getClaimsFromToken(token);
            if(claims == null){
                return null;
            }
            if(isTokenExpired(token)){
                return null;
            }
            String username = claims.get(USERNAME).toString();
            Integer userId = (Integer)claims.get(USERID);
            Object authors = claims.get(AUTHORITIES);
            Set<String> perms = new HashSet<>();
            if (authors instanceof List) {
                for (Object object : (List) authors) {
                    perms.add(((Map) object).get("authority").toString());
                }
            }
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));

            return new SecurityUser(userId,username,"",authorities);
        }

        return null;
    }

    /**
     * 生成令牌
     * @param userDetail 用户信息
     * @return 令牌
     */
    public String generateToken(SecurityUser userDetail) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERID,userDetail.getUserId());
        claims.put(USERNAME, userDetail.getUsername());
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, userDetail.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expireTime*60*1000);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 获取请求中的token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(TOKEN_PREFIX.length());
        }
        return token;
    }

    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    private  Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 判断令牌是否过期
     * @param token 令牌
     * @return
     */
    private  Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

}
