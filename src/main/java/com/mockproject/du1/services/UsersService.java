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

	public List<Users> getAllUser() {
		return usersMapper.sqlGetAllUserSelect();
	}

	public Users getUserByUsername(String username) {
		return usersMapper.sqlGetUserByUsernameSelect(username);
	}

	public Users getUserByEmail(String email) {
		return usersMapper.sqlGetUserByEmailSelect(email);
	}

	public List<GrantedAuthority> getAuthorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

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

	public boolean registerNewCustomer(Users user) {
		if (((getUserByUsername(user.getUsername())) != null) || ((getUserByEmail(user.getEmail())) != null))
			return false;
		usersMapper.sqlCreateUserInsert(user);
		return true;
	}

}
