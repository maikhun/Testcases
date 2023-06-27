package com.example.demo.config;

import com.example.demo.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Список допустимых адресов для не авторизованных пользователей
                        .requestMatchers("/", "/login", "/registration").permitAll()
                        // Все остальные адреса требуют авторизации
                        .anyRequest().authenticated()
                )
                // Конфигурация авторизации
                .formLogin((form) -> form
                        // Выбор своей страницы авторизации
                        .loginPage("/login")
                        // При успешной авторизации перемещает на указанный адрес
                        .successForwardUrl("/companies")
                        .permitAll()
                )
                // Конфигурация выхода из сессии пользователя
                .logout((logout) -> logout.permitAll()
                        // При успешном выходе из сессии перемещает на адрес формы авторизации
                        .logoutSuccessUrl("/login")
                );
        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Метод шифрования данных
     * @return Захешированное значение
     * */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}