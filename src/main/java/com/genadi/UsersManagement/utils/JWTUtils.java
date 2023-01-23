package com.genadi.UsersManagement.utils;

import com.genadi.UsersManagement.bean.UserLoginData;
import com.genadi.UsersManagement.bean.UserTypes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTUtils {

    private static final String JWT_KEY = "test";

    public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }


    public static String createJWT(UserLoginData userLoginData) {
        if(userLoginData.getUserType() == UserTypes.COMPANY){
            return createJWT(String.valueOf(userLoginData.getId()),  userLoginData.getUserName(),userLoginData.getUserName(), String.valueOf(userLoginData.getCompanyId()), 0);
        }
        else {
            return createJWT(String.valueOf(userLoginData.getId()), userLoginData.getUserName(), userLoginData.getUserName(), null,0);
        }

    }

    private static String createJWT( String id,String userName, String subject, String companyId, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(userName)
                .setAudience(companyId)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


    public static Integer getIdByToken(String token) {
        Claims claims = decodeJWT(token);
        return Integer.parseInt(claims.getId());
    }

    public static Integer validateToken(String token) throws Exception {
        Claims claims = decodeJWT(token);
        return Integer.parseInt(claims.getId());
    }


}
