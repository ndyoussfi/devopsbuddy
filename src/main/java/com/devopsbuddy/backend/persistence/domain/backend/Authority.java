package com.devopsbuddy.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

// GrantedAuthority is Spring's object to encapsulate the user role
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
