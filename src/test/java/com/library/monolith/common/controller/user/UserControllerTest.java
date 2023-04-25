package com.library.monolith.common.controller.user;

import com.library.monolith.common.controller.user.UserController;
import com.library.monolith.common.model.dto.user.LibraryUserDeleteDTO;
import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.dto.user.LibraryUserUpdateDTO;
import com.library.monolith.common.service.user.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    UserServiceImplementation userServiceImplementation;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void itShouldGetLibraryUserDtoById() {
        long id = 1L;
        LibraryUserDetailsDTO userDetailsDTO = new LibraryUserDetailsDTO();
        when(userServiceImplementation.getLibraryUserDetailsDto(id)).thenReturn(userDetailsDTO);

        ResponseEntity<LibraryUserDetailsDTO> responseEntity = userController.getLibraryUserDtoById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDetailsDTO, responseEntity.getBody());
    }

    @Test
    public void itShouldGetLibraryUserOverviewDtoList() {
        List<LibraryUserOverviewDTO> userOverviewDTOList = new ArrayList<>();
        when(userServiceImplementation.getLibraryUserOverviewDtoList()).thenReturn(userOverviewDTOList);

        ResponseEntity<List<LibraryUserOverviewDTO>> responseEntity = userController.getLibraryUserOverviewDtoList();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userOverviewDTOList, responseEntity.getBody());
    }

    @Test
    public void itShouldDeleteUser() {
        LibraryUserDeleteDTO deleteDTO = new LibraryUserDeleteDTO();
        String expectedResponse = "User deleted";
        when(userServiceImplementation.deleteUser(deleteDTO)).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = userController.deleteUser(deleteDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void itShouldUpdateUser() {
        String username = "user";
        LibraryUserUpdateDTO updateDTO = new LibraryUserUpdateDTO();
        String expectedResponse = "User updated";
        when(userServiceImplementation.editUser(username, updateDTO)).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = userController.updateUser(username, updateDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}
