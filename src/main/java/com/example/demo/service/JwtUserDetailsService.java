package com.example.demo.service;

import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApiDao;
import com.example.demo.model.DAOApi;
import com.example.demo.model.ApiDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ApiDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String apiId) throws UsernameNotFoundException {
		DAOApi user = userDao.findByApiId(apiId);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + apiId);
		}
		return new org.springframework.security.core.userdetails.User(user.getApiId(), user.getApiKey(),
				new ArrayList<>());
	}
	
	public DAOApi save(ApiDTO user) {
		DAOApi newUser = new DAOApi();
		newUser.setApiId(user.getApiId());
		newUser.setApiKey(bcryptEncoder.encode(user.getApiKey()));
		return userDao.save(newUser);
	}
}