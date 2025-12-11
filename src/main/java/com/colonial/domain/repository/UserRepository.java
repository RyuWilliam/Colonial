package com.colonial.domain.repository;

import com.colonial.domain.enums.Role;
import com.colonial.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Integer id);
    List<User> findAll();
    void deleteById(Integer id);
    boolean existsById(Integer id);

    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    boolean existsByEmail(String email);
}