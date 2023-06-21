package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepo;
	
	

	@Override
	public ResponseEntity<?> addUser(User user) {
		System.out.println(user+"from fornt end");
		if(user!=null)
		{
			if(userRepo.existsByEmail(user.getEmail())){
				System.out.println("already present"+user.getEmail());
				return new ResponseEntity<>(user.getEmail()+" this Email is already exists! try with another one ", HttpStatus.BAD_REQUEST);
			}
			user.setRole("user");
			userRepo.saveAndFlush(user);
			return new ResponseEntity<>( HttpStatus.OK);
		}
		return new ResponseEntity<>("Something went wrong try some time later",HttpStatus.BAD_REQUEST);
	}

	@Override
	public boolean loginUser(String username, String password) {

		User user1 = userRepo.validateUser(username, password);
		if(user1!=null)
		{
			return true;
		}

		return false;
	}

	@Override
	public List<User> getAllUsers() {
	
		List<User> userList = userRepo.findAll();
		
		if(userList!=null & userList.size() >0)
		{
			return userList;
		}
		else
			return null;
	}
	

	@Override
	public String getRoleByUserAndPass(String username, String password) {
		
		String r=userRepo.GetRole(username, password);
		return r;
	}

	@Override
	public ResponseEntity<?> UpdatePass(String email, String password, String confirmPassword, String answer,String question) {

		if(userRepo.existsByEmail(email)){
			System.out.println(email+password+confirmPassword+question+answer+"from inside service");
			Optional<User> userdata = userRepo.findByEmail(email);
			if(userdata.get().getQuestion().equals(question)){
				if(userdata.get().getAnswer().equals(answer)){
					userRepo.updatePass(password,confirmPassword,email);
					return new ResponseEntity<>(HttpStatus.OK);
				}
				return new ResponseEntity<>("wrong answer",HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>("question wrong",HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>("user doesn't exist",HttpStatus.NOT_FOUND);
	}
}














