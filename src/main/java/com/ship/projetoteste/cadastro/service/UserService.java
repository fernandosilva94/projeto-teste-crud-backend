package com.ship.projetoteste.cadastro.service;

import com.ship.projetoteste.cadastro.dto.UserDTO;
import jakarta.validation.Valid;
import com.ship.projetoteste.cadastro.model.User;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ship.projetoteste.cadastro.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(@Valid UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este email.");
        }
        if (userRepository.existsByCpf(userDTO.getCpf())) {
            throw new IllegalArgumentException("Já existe um usuário com o mesmo CPF");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Long userId, @Valid UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        BeanUtils.copyProperties(userDTO, user, "id");
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(userId);
    }
}
