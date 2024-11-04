package com.websystem.LoginSystem.Repository;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {
    String getEmail();

    @Override
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    String getPassword();

    @Override
    default String getUsername() {
        return "";
    }

}

