package com.campestre.clube.backend_application.controller.dtos;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DetailsAccountDto implements UserDetails {
    private String name;
    private String email;
    private String password;
    private AccessTypeEnum access;

    public DetailsAccountDto(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.access = account.getAccess();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
