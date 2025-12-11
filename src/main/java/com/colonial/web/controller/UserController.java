package com.colonial.web.controller;


import com.colonial.domain.model.User;
import com.colonial.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();

    }



}
