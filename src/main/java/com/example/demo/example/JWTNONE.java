package com.example.demo.example;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * @Author: swh
 * @Date: 2020 04 2020/4/1 12:48
 * @Version: 1.0
 */
public class JWTNONE {
    //有效期为
    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "itcast";
    public static String createJWT(String id, String subject, Long ttlMillis) {
        //定义jwt签名的算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        //将当前时间转换日期类型
        Date now = new Date(nowMillis);
        //将当前时间+超时时间
        if(ttlMillis==null){
            ttlMillis=JWTNONE.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        //将时间定义为date类型
        Date expDate = new Date(expMillis);
        //获取签名时候使用的密钥
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("admin")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWTNONE.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    public static void main(String[] args) throws Exception {
            String jwt = createJWT("swh","123",100L);
            System.out.println("q2we");
    }
}
