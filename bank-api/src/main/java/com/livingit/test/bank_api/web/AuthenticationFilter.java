package com.livingit.test.bank_api.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.livingit.test.bank_api.error.ForbidenException;
import com.livingit.test.bank_api.error.UnauthorizedException;
import com.livingit.test.bank_api.service.JwtValidatorService;

@Component
//comment this extends part in order to disable security due is not full implemented
//extends OncePerRequestFilter
public class AuthenticationFilter  {

	@Autowired
	private JwtValidatorService jwtValidator;

//	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			
			if (!jwtValidator.validateToken(jwtToken)) {
				throw new UnauthorizedException();
			}

			String balanceRole = jwtValidator.getBalanceRoleFromToken(jwtToken);
			if(!"editor".equals(balanceRole)) {
				throw new ForbidenException();
			}
		} else {
//			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		chain.doFilter(request, response);
	}

}
