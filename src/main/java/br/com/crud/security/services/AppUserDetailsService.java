package br.com.crud.security.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.crud.entities.User;
import br.com.crud.services.UserService;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), getPermissions(user));
	}

	private Collection<? extends GrantedAuthority> getPermissions(User user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getDescription())));
		return authorities;
	}

}
