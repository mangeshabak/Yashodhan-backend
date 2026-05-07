package com.yashodhan.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashodhan.entity.User;
import com.yashodhan.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public Page<User> getAllUsers(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size);

        if (search == null || search.isEmpty()) {
            return userRepository.findAll(pageable);
        }

        return userRepository.searchUsers(search, pageable);
    }
 
    public User saveUser(User user) {
        return userRepository.save(user);
    }

   
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
    public User getProfile(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    // =====================================================
    // UPLOAD PROFILE IMAGE
    // =====================================================

    public User uploadProfileImage(
            Long id,
            MultipartFile file
    ) throws IOException {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // save image as byte[]
        user.setProfileImage(file.getBytes());

        return userRepository.save(user);
    }
}