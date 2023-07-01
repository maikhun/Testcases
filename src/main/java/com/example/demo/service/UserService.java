package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Регистрация пользователя
     * @param user Регистрируемый пользователь
     * @return Статус операции
     * */
    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    /**
     * Нахождение данных пользователя по логину
     * @param email Логин пользователя
     * @return Пользователь
     * */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
