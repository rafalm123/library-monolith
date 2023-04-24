package com.library.monolith.common.controller.user;

import com.library.monolith.common.model.dto.user.*;
import com.library.monolith.common.service.user.UserServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("library/users")
public class UserController {

    private UserServiceImplementation userServiceImplementation;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserDetailsDTO> getLibraryUserDtoById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(userServiceImplementation.getLibraryUserDetailsDto(id));
    }

    @GetMapping("")
    public ResponseEntity<List<LibraryUserOverviewDTO>> getLibraryUserOverviewDtoList() {
        return ResponseEntity.ok(userServiceImplementation.getLibraryUserOverviewDtoList());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(
            @RequestBody LibraryUserDeleteDTO libraryUserDeleteDTO
    ) {
        return ResponseEntity.ok(userServiceImplementation.deleteUser(libraryUserDeleteDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestParam String username,
            @RequestBody LibraryUserUpdateDTO libraryUserUpdateDTO
    ) {
        return ResponseEntity.ok(userServiceImplementation.editUser(username,libraryUserUpdateDTO));
    }

}