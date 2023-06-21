package com.example.demo;


import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@AutoConfigureMockMvc
public class UserRepo
    {
        @Autowired
        private UserRepository userRepo;

        private User user = new User();// real object

        @BeforeEach
        public void init()
        {
            //List<String> userList = new ArrayList<String>();
            user.setId(101);
            user.setUsername("sam");
            user.setRole("user");
            user.setEmail("sam@gmail.com");
            user.setMobileno("123445");
            user.setQuestion("Secret");
            user.setAnswer("tom");
            user.setPassword("abc");
            user.setConfirmPassword("abc");
            //userList.add("Guest User");
            //userList.add("Admin user");
            //user.setUserList(userList);
        }

        @Test
        public void saveUserSuccess() throws Exception
        {
            User user1 = null;

            userRepo.save(user);

            user1 = userRepo.findByEmail(user.getEmail()).get();
            assertEquals(user.getEmail(), user1.getEmail());
        }

//        @Test
//        public void saveUserFailure() throws Exception
//        {
//            User user1 = null;
//
//
//
//            if(userRepo.findAll().toString().isEmpty())
//            {
//                userRepo.save(user);
//                user1 = userRepo.findByEmail(user.getEmail()).get();
//
//            }
//
//
//
//            assertNull(user1);
//        }

    }

