package com.colonial.domain.service;

import com.colonial.domain.model.User;
import com.colonial.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    public boolean existsById(Integer id){
        return userRepository.existsById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
