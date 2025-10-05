package com.colonial.persistence;

import com.colonial.domain.model.User;
import com.colonial.domain.repository.UserRepository;
import com.colonial.persistence.crud.UserJpaRepository;
import com.colonial.persistence.entity.UserEntity;
import com.colonial.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserJpaRepository userRepository;

    public UserRepositoryImpl(UserMapper userMapper, UserJpaRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity entity = userMapper.toEntity(user);
        return userMapper.toUser(userRepository.save(entity));
    }

    @Override
    public User findById(int id) {
        return userMapper.toUser(userRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(int id, User user) {
        return null;
    }
}
