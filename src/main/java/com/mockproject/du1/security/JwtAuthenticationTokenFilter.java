package com.mockproject.du1.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.mockproject.du1.model.Users;
import com.mockproject.du1.services.JwtService;
import com.mockproject.du1.services.UsersService;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final static String TOKEN_HEADER = "authorization";

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);
		if (authToken != null) {
			if (jwtService.validateTokenLogin(authToken)) {
				String username = jwtService.getUsernameFromToken(authToken);
//				HttpSession session = httpRequest.getSession();
//				session.setAttribute("usernameLogin", username);
				Users users = userService.getUserByUsername(username);
				if (users != null) {
					boolean enabled = true;
					boolean accountNonExpired = true;
					boolean credentialsNonExpired = true;
					boolean accountNonLocked = true;
					UserDetails userDetail = new User(username, users.getPassword(), enabled, accountNonExpired,
							credentialsNonExpired, accountNonLocked, userService.getAuthorities(users));
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetail, null, userDetail.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

					SecurityContextHolder.getContext().setAuthentication(authentication);
					UserDetails userDetail1= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					userDetail1.getUsername();
				}
			}
		}
		chain.doFilter(request, response);
	}
}
