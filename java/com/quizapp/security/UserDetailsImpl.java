package com.quizapp.security;

import com.quizapp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.*;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;

    public UserDetailsImpl(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // No roles for now
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
