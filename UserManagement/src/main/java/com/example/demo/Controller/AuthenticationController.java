  package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.example.demo.Exception.UserNotFound;
import com.example.demo.Filter.JwtUtils;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequestMapping("auth/v1")
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController 
{
	
	private Map<String,String> mapObj = new HashMap<String,String>();
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/addUser")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		return userService.addUser(user);
	}
	
	
	public String generateToken(String username, String password) throws ServletException
	{
		String jwtToken;

		if(username==null || password == null)
		{
			throw new ServletException("Please enter valid username and password");
		}
		
		boolean flag= userService.loginUser(username, password);
		String role=userService.getRoleByUserAndPass(username,password);
		System.out.println(role+"from dbb");
		
		if(!flag)
		{
			throw new ServletException("Invalid credentials");
			
		}
		else
		{
			jwtToken= Jwts.builder().setSubject(username).claim("role",role).setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis()+ 3000000))
						.signWith(SignatureAlgorithm.HS256, "secret key").compact();
		}
		
		return jwtToken;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> performLogin(@RequestBody User user)
	{
		System.out.println(user.getUsername());
		boolean check=userService.loginUser(user.getUsername(),user.getPassword());



		if(check){
			String role=userService.getRoleByUserAndPass(user.getUsername(), user.getPassword());
			System.out.println(role);
			try
			{
				String jwtToken = generateToken(user.getUsername(), user.getPassword());
				if(role.equalsIgnoreCase("admin"))
				{
					mapObj.put("message", "Admin successfully logged in");
					mapObj.put("jwtToken",jwtToken);
					mapObj.put("role",role);
					return new ResponseEntity<>(mapObj, HttpStatus.CREATED);

				}
				else if(role.equalsIgnoreCase("user"))
				{
					mapObj.put("message", "User successfully logged in");
					mapObj.put("jwtToken", jwtToken);
					mapObj.put("role",role);
					return new ResponseEntity<>(mapObj, HttpStatus.CREATED);
				}

			}


			catch( ServletException e)
			{
				mapObj.put("message", "User not logged in!");
				mapObj.put("jwtToken", null);
				mapObj.put("user not found",null);
			}

		}

		return new ResponseEntity<>(mapObj, HttpStatus.FORBIDDEN);

	}


	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
		System.out.println(token+"---------");
		if (jwtUtils.validateJwtToken(token)) {
			// return ResponseEntity.ok("Valid token");
			System.out.println("-------validate-------");
			Map<String, String> userInfo = new HashMap<>();
			String authToken = token.substring(7);
			String username = jwtUtils.getUserNameFromJwtToken(authToken);
			String role = jwtUtils.getRoleFromToken(authToken);
			userInfo.put(username, role);
			System.out.println(userInfo);
			return ResponseEntity.status(HttpStatus.OK).body(userInfo);
		} else {
			System.out.println("-------***********-------");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		}
	}
	
	
	
	
}















