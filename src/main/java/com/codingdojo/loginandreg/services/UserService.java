package com.codingdojo.loginandreg.services;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingdojo.loginandreg.models.User;
import com.codingdojo.loginandreg.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    		this.userRepository = userRepository;
    		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    

}
