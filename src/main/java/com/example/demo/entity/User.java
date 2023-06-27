package com.example.demo.entity;

import com.example.demo.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Пользователь
 * */
@Entity
@Table(name = "usr")
@Data
public class User implements UserDetails {

    // Идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Имя
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    // Фамилия
    @Column(name = "secondName", nullable = false, length = 100)
    private String secondName;

    // Адрес электронной почты
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // Пароль
    @Column(name="password", nullable = false,length = 100)
    private String password;

    // Статус пользователя
    @Column(name = "active")
    private boolean active;

    // Роль пользователя
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return active;
    }
}
