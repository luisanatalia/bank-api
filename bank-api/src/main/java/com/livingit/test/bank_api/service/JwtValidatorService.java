package com.livingit.test.bank_api.service;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidatorService  implements Serializable {

	private static final long serialVersionUID = 4082103484155034644L;

	//don't do this is a real implementation, is not secure
	private String secret = "1234567890";

	/**
	 * return user name from jwt claims
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(final String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * return jwt expiration date
	 * @param token
	 * @return
	 */
	public Date getExpirationDateFromToken(final String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	/**
	 * return custom claim balanceRole
	 * @param token
	 * @return
	 */
	public String getBalanceRoleFromToken(final String token) {
		Claims claims = getAllClaimsFromToken(token);
		return claims.get("balance-role", String.class);
	}

	private <T> T getClaimFromToken(final String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    
	/**
	 * return all claims that jwt contains
	 * @param token
	 * @return
	 */
	private Claims getAllClaimsFromToken(final String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * return if the jwt is expired
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(final String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * validate if the given token is not expired and has all required information
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token) {
		String balanceRole = getBalanceRoleFromToken(token);
		return (balanceRole != null && !isTokenExpired(token));
	}
}

