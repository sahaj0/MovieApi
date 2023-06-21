package com.example.demo;


import com.example.demo.Controller.UserController;
import com.example.demo.Model.User;
import com.example.demo.Service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userC;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userC).build();
    }
    List<User> userList = new ArrayList<User>();

    @Test
    public void getAllUsersSuccess() throws Exception
    {
        User user = new User();
        user.setId(101);
        user.setUsername("sam");
        user.setRole("user");
        user.setEmail("sam@gmail.com");
        user.setMobileno("123445");
        user.setQuestion("Secret");
        user.setAnswer("tom");
        user.setPassword("abc");
        user.setConfirmPassword("abc");
        userList.add(user);
        when(userService.getAllUsers()).thenReturn(userList);

        List<User> uList = userService.getAllUsers();
        assertEquals(userList, uList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllUsers/sahaj").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



}
