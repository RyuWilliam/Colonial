package com.colonial.persistence;

import com.colonial.domain.enums.Role;
import com.colonial.domain.model.User;
import com.colonial.domain.repository.UserRepository;
import com.colonial.persistence.crud.UserJpaRepository;
import com.colonial.persistence.entity.UserEntity;
import com.colonial.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserMapper userMapper, UserJpaRepository userJpaRepository) {
        this.userMapper = userMapper;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        entity.setRole(Role.CONSUMER);
        userJpaRepository.save(entity);
        return userMapper.toUser(entity);
    }

    @Override
    public Optional<User> findById(Integer id) {
        UserEntity entity = userJpaRepository.findById(id).orElse(null);

        if (entity == null) {
            return Optional.empty();
        }

        User user = userMapper.toUser(entity);
        return Optional.of(user);
    }


    @Override
    public List<User> findAll() {
        return userMapper.toUsers(userJpaRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity entity = userJpaRepository.findByEmail(email).orElse(null);
        if(entity == null){
            return Optional.empty();
        }
        User user = userMapper.toUser(entity);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        UserEntity entity = userJpaRepository.findByPhone(phone).orElse(null);
        if(entity == null){
            return Optional.empty();
        }
        User user = userMapper.toUser(entity);
        return Optional.of(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}
