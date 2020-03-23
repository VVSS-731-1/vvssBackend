package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DtoMapping dtoMapping;

    private User user;

    private UserDTO userDTO;

    private NullPointerException nullPointerException;

    @Mock
    private List<User> userList;

    @Test
    private void findAll() {
        when(dtoMapping.dtoToUser(userDTO)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);
    }

    @Test
    private void save() {
        when(userService.save(userDTO)).thenReturn(userDTO);
        when(userService.save(null)).thenThrow(nullPointerException);
    }

    @Test
    private void findByName() {
        when(userService.findByName("test")).thenReturn(userDTO);
        when(userService.findByName("")).thenReturn(null);
        when(userService.findByName(null)).thenThrow(nullPointerException);
    }

    @Test
    private void login() {
        when(userService.login("test", "password")).thenReturn(userDTO);
        when(userService.login("","")).thenReturn(null);
        when(userService.login(null,null)).thenThrow(nullPointerException);
    }
}