package com.wide.test.util;

import com.wide.test.dto.MasterUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtTokenUtil implements Serializable {




		private String secret = "ZtAtxAuQIKCPHzCGHxC4yuSyM1EP7TVQxPgYTio1iw162cqLnEQ0scLpkiXahYiHnURUBndih6vSFC9ceGObkA";


	public  String getEmailFromToken(String token) {
                try {   
                    return getClaimFromToken(token, Claims::getSubject);      
                } catch (Exception e) {
                    return null;
                }
	}

	public Map<String, Object> decodeToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public String getUserNameFromToken(String token){
		try{
			Map<String, Object> decodedToken = this.decodeToken(token);
			return (String) decodedToken.get("userName");

		}catch (Exception e){
			return null;
		}
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            try {
                final Claims claims = getAllClaimsFromToken(token);
                return claimsResolver.apply(claims);
            } catch (Exception e) {
                return null;
            }
	}

	private Claims getAllClaimsFromToken(String token) {
                try {
                    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                } catch (Exception e) {
                    return null;
                }
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);

                if(expiration == null){
                    return true;
                }

                Date a = new Date();

		Boolean s = expiration.before(a);
                return s;
	}

	private Boolean ignoreTokenExpiration(String token) {
		return false;
	}

	public String generateToken(MasterUserDTO userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userName",userDetails.getUserName());
		return doGenerateToken(claims, userDetails.getUserName());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60*1000*100)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, MasterUserDTO userDetails) {
		final String username = getEmailFromToken(token);
                if(username == null){
                    return false;
                }
		return (username.equals(userDetails.getUserName()) && !isTokenExpired(token));
	}
}
