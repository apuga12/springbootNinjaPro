package com.industries.stark.springbootNinja.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.industries.stark.springbootNinja.entity.UserRole;
import com.industries.stark.springbootNinja.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Override
	// Llamara a userRepository(username) y transforma en un objeto UserDetails
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.industries.stark.springbootNinja.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}
	
	
	// Construye obj User de Sprong Security a partir de nuestro usuario en BD
	private User buildUser(com.industries.stark.springbootNinja.entity.User user, List<GrantedAuthority> authorities){
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), 
				//accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
				true, true, true, authorities);
	}
	
	// Transforma el set de Roles de usuario a Listado de Roles que entiende SpringSecurity
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(UserRole userRole : userRoles){
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));			
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

}
