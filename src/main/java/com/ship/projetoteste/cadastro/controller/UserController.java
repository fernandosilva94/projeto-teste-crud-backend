package com.ship.projetoteste.cadastro.controller;

import com.ship.projetoteste.cadastro.dto.UserDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.ship.projetoteste.cadastro.model.User;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ship.projetoteste.cadastro.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
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
        List<UserDTO> userDTOS = users.stream().map(user -> new UserDTO())
                                               .collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUse(@PathVariable Long id, @Valid @RequestBody UserDTO userDetails) {
        User updateUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("email não encontrado"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
