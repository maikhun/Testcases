package com.example.demo.entity.enums;

import org.springframework.security.core.GrantedAuthority;
/**
 * Перечисление
 * */
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
