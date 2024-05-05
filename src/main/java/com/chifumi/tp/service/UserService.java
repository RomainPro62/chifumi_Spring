package com.chifumi.tp.service;

import com.chifumi.tp.service.entity.User;
import com.chifumi.tp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return (user != null) ? user.getId() : null;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
