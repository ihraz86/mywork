package com.intervest.staysureapi.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.intervest.staysureapi.model.Role;
import com.intervest.staysureapi.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private Map<String, User> users = new HashMap<String, User>();
	
	private void init() {
		User mystaysure = new User();
		mystaysure.setId(1);
		mystaysure.setName("MyStaysure");
		mystaysure.setLogin("mystaysure");
		mystaysure.setPassword("mystaysurepassword");
		
		Role role = new Role();
		role.setId(1);
		role.setName("ROLE_ADMIN");
		
		Set<Role> roles = new HashSet<>();
		
		mystaysure.setRoles(roles);
		
		users.put("mystaysure", mystaysure);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		init();
		User user = users.get(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return new UserRepositoryUserDetails(user);
	}

	private final static class UserRepositoryUserDetails extends User implements UserDetails {

		private static final long serialVersionUID = 1L;

		private UserRepositoryUserDetails(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return getRoles();
		}

		@Override
		public String getUsername() {
			return getLogin();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}

}
