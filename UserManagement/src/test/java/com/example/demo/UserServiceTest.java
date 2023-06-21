package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Mock
    UserServiceImpl userServiceImpl;


    @Test
    public void getAllUsers() {

        User testUser=new User();

       testUser.setUsername("Usernametest"); ;
        testUser.setPassword("testpassword");

          testUser.setConfirmPassword("testpassword");
          testUser.setRole("testRole");

         testUser.setEmail("testEmail");

         testUser.setMobileno("9412345");
        testUser.setQuestion("testQuestion");
         testUser.setAnswer("testAnswer");
        List<User> list=new ArrayList<>();
        list.add(testUser);

        when(userServiceImpl.getAllUsers()).thenReturn(list);
        List<User> MList =userServiceImpl.getAllUsers();
        Assertions.assertEquals(list, MList);



    }


}