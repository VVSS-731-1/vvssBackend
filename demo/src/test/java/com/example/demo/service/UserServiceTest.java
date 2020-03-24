package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.ProjectDto;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Mock
    private DtoMapping dtoMapping;

    @Test
    public void findAll() {
        when(userRepository.findAll()).thenReturn(Stream.of(new User(), new User()).collect(Collectors.toList()));
        Assert.assertEquals(2, userService.findAll().size());
    }
}
