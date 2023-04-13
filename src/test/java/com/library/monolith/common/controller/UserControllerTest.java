package com.library.monolith.common.controller;

import com.library.monolith.common.controller.user.UserController;
import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.dto.user.UserServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserServiceImplementation userServiceImplementation;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private List<LibraryUserOverviewDTO> userOverviewList;
    private LibraryUserDetailsDTO userDetails;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userDetails = new LibraryUserDetailsDTO(
                "John",
                "Doe",
                "johndoe",
                new Timestamp(System.currentTimeMillis()),
                "johndoe@example.com",
                "123 Main St",
                "Anytown",
                "CA",
                "90210",
                "USA",
                123456L
        );
        userOverviewList = List.of(
                new LibraryUserOverviewDTO(
                        "John",
                        "Doe",
                        1231233L,
                        "johndoe@example.com"
                ),
                new LibraryUserOverviewDTO(
                        "Jane",
                        "Doe",
                        342132144L,
                        "janedoe@example.com"
                )
        );
    }

    @Test
    public void testGetLibraryUserDtoById() throws Exception {
        Long userId = 1L;
        when(userServiceImplementation.getLibraryUserDetailsDto(userId)).thenReturn(userDetails);
        mockMvc.perform(get("/library/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.surname", is("Doe")))
                .andExpect(jsonPath("$.nickname", is("johndoe")))
                .andExpect(jsonPath("$.email", is("johndoe@example.com")))
                .andExpect(jsonPath("$.libraryCode", is(123456)));
        verify(userServiceImplementation).getLibraryUserDetailsDto(userId);
    }

}
