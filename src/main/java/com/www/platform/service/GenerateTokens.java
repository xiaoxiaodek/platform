package com.www.platform.service;

import com.www.platform.util.ApiKey;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by upsmart on 17-8-7.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:45
 */
public class GenerateTokens{

    //Sample method to construct a JWT
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(ApiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }



    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        String key = ApiKey.getSecret();
        Claims claims=null;
        try{claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
            }catch (SignatureException | MalformedJwtException e){
                //
                // don't trust the JWT!
                // jwt 解析错误
            }catch (ExpiredJwtException e){
                //
                // jwt 已经过期，在设置jwt的时候如果设置了过期时间，
                // 这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
            }
        return claims;
    }

    /**
     * 生成subject信息
     * @param user
     * @return
     */
//    public static String generalSubject(t_user user){
//        JSONObject jo = new JSONObject();
//        jo.put("userId", user.getId());
//        jo.put("mobile", user.getMobile());
//        return jo.toJSONString();
//    }

    /**
     * 由字符串生成加密key
     * @return
     */
//    public SecretKey generalKey(){
//        String stringKey = jianshu+Constant.JWT_SECRET;
//        byte[] encodedKey = Base64.decodeBase64(stringKey);
//        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
//        return key;
//    }

}
