package com.guai.common.utils;

import com.guai.common.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final String USERID = "userid";//用户id
    private static final String CREATED = "created";//创建时间
    private static final String USERNAME = "username";//用户账号
    private static final String AUTHORITIES = "authorities";//权限列表
    private static final String TOKEN_PREFIX = "Bearer "; //令牌前缀

    private static final String REDIS_KEY = "tokenKey";// 用户信息存入reidskey

    private static final Long MIN_REFRESH_TIME = 5 * 60 * 1000L; //token刷新的最小时长

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
            String key = claims.get(REDIS_KEY).toString();
            // 1 从redis中获取用户信息
            SecurityUser user =(SecurityUser) redisTemplate.opsForValue().get(key);
            // 2 判断是否到reids中用户信息过期刷新时间
            long user_expireTime = user.getExpireTime();
            long currentTime = DateUtil.getNowDateTimestamp();
            if (user_expireTime - currentTime <= MIN_REFRESH_TIME){
                // 2.1 刷新用户信息
                user.setExpireTime(user.getExpireTime()+expireTime*60*1000);
                refreshToken(user,key);
            }
//            String username = claims.get(USERNAME).toString();
//            Integer userId = (Integer)claims.get(USERID);
//            Object authors = claims.get(AUTHORITIES);
//            Set<String> perms = new HashSet<>();
//            if (authors instanceof List) {
//                for (Object object : (List) authors) {
//                    perms.add(((Map) object).get("authority").toString());
//                }
//            }
//            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));

            return  user;
        }

        return null;
    }

    /**
     * 生成令牌
     * @param userDetail 用户信息
     * @return 令牌
     */
    public String generateToken(SecurityUser userDetail) {
        userDetail.setExpireTime(DateUtil.getNowDateTimestamp()+expireTime*60*1000); //设置过期时间
        String key = IdUtil.getUNID();// reids key
        System.out.println(key);
        refreshToken(userDetail,key);
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(REDIS_KEY,key);
//        claims.put(USERID,userDetail.getUserId());
//        claims.put(USERNAME, userDetail.getUsername());
//        claims.put(CREATED, new Date());
//        claims.put(AUTHORITIES, userDetail.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
//        Date expirationDate = new Date(System.currentTimeMillis() + expireTime*60*1000);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
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

    /**
     *
     * @param user 用户信息
     * @param key 存入redis的key
     */
    private void refreshToken(SecurityUser user,String key){
        redisTemplate.opsForValue().set(key,user,expireTime, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

}
