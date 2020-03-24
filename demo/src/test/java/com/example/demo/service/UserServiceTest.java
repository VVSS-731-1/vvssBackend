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

    @Test
    public void findById() {
        int id = 89;
        when(userRepository.findById(id)).thenReturn(Optional.of(new User()));
        Assert.assertEquals(Optional.of(new User()), userService.findById(id));
    }

    @Test
    public void save() {
        UserDTO userDTO = getUserDTO();
        when(userService.save(userDTO)).thenReturn(userDTO);
        Assert.assertEquals(userDTO, userDTO);
    }

    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setCounter(0);
        userDTO.setAdmin(false);
        userDTO.setPassword("password");
        userDTO.setStatus(true);
        userDTO.setFirstName("paul");
        userDTO.setLastName("paul");
        userDTO.setProjects(Stream.of(new ProjectDto()).collect(Collectors.toList()));
        userDTO.setSupervising(Stream.of(new UserDTO()).collect(Collectors.toSet()));
        userDTO.setSupervisor(new UserDTO());
        userDTO.setUsername("hatz");
        userDTO.setEmail("myEmail@email.com");
        return userDTO;
    }
}