package com.yashodhan.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yashodhan.entity.User;
import com.yashodhan.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profile/{id}")
	public User getProfile(@PathVariable Long id) {
	    return userService.getUserById(id);
	}
	
	  // GET ALL EMPLOYEES
	 @GetMapping("/all")
	    public Page<User> getAllUsers(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(required = false) String search
	    ) {
	        return userService.getAllUsers(page, size, search);
	    }


    // ADD OR UPDATE EMPLOYEE (SAME API)
    @PostMapping("/save")
    public User saveOrUpdate(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // DELETE EMPLOYEE (POST ONLY)
    @PostMapping("/delete")
    public String delete(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        userService.deleteUser(id);
        return "Deleted successfully";
    }
    
    @GetMapping("/profileImage/{id}")
    public ResponseEntity<User> getProfileImage(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                userService.getProfile(id)
        );
    }

    // =====================================================
    // UPLOAD PROFILE IMAGE
    // =====================================================

    @PostMapping(value="/upload-profile/{id}",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> uploadProfile(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        User updatedUser =
                userService.uploadProfileImage(id, file);

        return ResponseEntity.ok(updatedUser);
    }
}
