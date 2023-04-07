package com.library.monolith.common.controller.user;

import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.dto.user.UserServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("library/users")
public class UserController {

    private UserServiceImplementation userServiceImplementation;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserDetailsDTO> getLibraryUserDtoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userServiceImplementation.getLibraryUserDetailsDto(id));
    }

    @GetMapping("")
    public ResponseEntity<List<LibraryUserOverviewDTO>> getAllUserOverviewDtos(){
        return ResponseEntity.ok(userServiceImplementation.getLibraryUserOverviewDtoList());
    }

}