package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.User;
import org.springframework.http.ResponseEntity;

public interface UserService 
{
	public ResponseEntity<?> addUser(User user);// user registration

	public boolean loginUser(String username, String password);// login
	
	public List<User> getAllUsers();// will be visible only if you are logged in

	public String getRoleByUserAndPass(String username, String password);


	ResponseEntity<?> UpdatePass(String email, String password, String confirmPassword, String answer, String question);
}
