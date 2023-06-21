package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class UserController
{
	@Autowired
	private UserService uService;
	
	@GetMapping("/getAllUsers/{username}")
	public ResponseEntity<?> getAllUsers(@PathVariable("username") String username)
	{
		if(username.equalsIgnoreCase("Sahaj")) {
		List<User> userlist = uService.getAllUsers();
		System.out.println(userlist);
		if(userlist!=null)
		{
			return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
		}

		return new ResponseEntity<String>("userlist is empty", HttpStatus.FORBIDDEN);
		}
		System.out.println(username);
		return new ResponseEntity<String>("Not Authorised Admin",HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getRole") 
	public String getRole(@RequestBody User u) {
		return this.uService.getRoleByUserAndPass(u.getUsername(), u.getPassword());
	}

	@PutMapping("/forget")
	public ResponseEntity<?> updatePassword(@RequestBody User u) {
		System.out.println(u+"----------------");
		return uService.UpdatePass(u.getEmail(),u.getPassword(),u.getConfirmPassword(),u.getAnswer(),u.getQuestion());

	}
}
