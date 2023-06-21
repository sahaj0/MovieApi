package com.example.demo.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>
{
	
	@Query(value="select u from User u where u.username= :username and u.password= :password")
	public User validateUser(String username, String password);//login

	@Query(value="select u.role from User u where u.username=:username and u.password=:password")
	public String GetRole(String username, String password);

//	@Query(value = "select * from user where email=?1",nativeQuery = true)
//	Boolean findByEmail(String email);

	@Query(value = "select * from user where email=?1",nativeQuery = true)
	Optional<User> findByEmail(String email);


	Boolean existsByEmail(String email);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE  User u SET u.password =?1,u.confirmPassword=?2 where u.email=?3")
	void updatePass(String password, String confirmPassword,String email);
}
