package com.colonial.domain.repository;

import com.colonial.domain.model.User;

public interface UserRepository {

    User saveUser(User user);

    User findById(int id);

    void deleteUser(int id);

    User updateUser(int id, User user);

}
