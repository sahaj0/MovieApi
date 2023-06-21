package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User 
{	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String question;
	private String answer;
	private String mobileno;
	private String role;



}
