package com.ship.projetoteste.cadastro.controller;

import com.ship.projetoteste.cadastro.dto.UserDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.ship.projetoteste.cadastro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ship.projetoteste.cadastro.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    @Transactional
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDTO) {
        User newUser = userService.addUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity <List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
