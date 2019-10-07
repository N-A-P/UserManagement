package com.mockproject.du1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.mockproject.du1.mapper.UsersMapper;
import com.mockproject.du1.model.Users;
@Service
public class UsersService {

	@Autowired
	private UsersMapper usersMapper;

	public Users getUserByUsername(String username) {
		
		return usersMapper.sqlGetUserByUsernameSelect(username);
	}

	public List<GrantedAuthority> getAuthorities(Users user) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return authorities;
	}

	/**
	 * Get User Login
	 *
	 * @param users
	 * @return Results returned
	 */
	public boolean checkLogin(Users user) {

		try {

			if (usersMapper.sqlCheckLoginSelect(user.getUsername(), user.getPassword()) == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace(); 
		}

		return false;
	}

}
