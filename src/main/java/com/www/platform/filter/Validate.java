package com.www.platform.filter;

import com.www.platform.util.ApiKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by upsmart on 17-8-7.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午4:39
 */
public class Validate {
    //Sample method to validate and read the JWT
    private void parseJWT(String jwt) {
        Claims claims=null;
        //This line will throw an exception if it is not a signed JWS (as expected)
       try{
            claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(ApiKey.getSecret()))
                .parseClaimsJws(jwt).getBody();
           //ok,we trust this JWT
       }catch (SignatureException e){
           //we don't trust this JWT
       }

        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}
