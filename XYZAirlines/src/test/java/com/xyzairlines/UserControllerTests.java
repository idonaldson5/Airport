package com.xyzairlines;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyzairlines.controllers.UserController;
import com.xyzairlines.models.User;
import com.xyzairlines.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.FixMethodOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User("UserName", "12345");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);

        this.mockMvc.perform(post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                //.andExpect(jsonPath("$.id", is(user.getId().intValue())))
                //.andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(status().isOk());
//not sure about is import
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void gettingUserApiTest() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Iona", "12222sdcds"));
        users.add(new User("Kirsty", "12222vvsvds"));
        when(userRepository.findAll()).thenReturn(users);
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userName", is("Iona")))
                .andExpect(jsonPath("$[1].userName", is("Kirsty")))
                .andExpect(status().isOk());
        verify(userRepository, times(1)).findAll();
    }



}
