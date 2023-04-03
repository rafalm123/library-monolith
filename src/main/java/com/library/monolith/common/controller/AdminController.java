package com.library.monolith.common.controller;

import com.library.monolith.common.model.entity.user.LibraryUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/library")
public class AdminController {

    private static final List<LibraryUser> USERS = Arrays.asList(
            new LibraryUser("Nick","Nick@gmail.com"),
            new LibraryUser("Tom","Tom@gmail.com"),
            new LibraryUser("Mike","Mike@gmail.com")
    );

    public List<LibraryUser> getAllUsers(){
        return USERS;
    }

    public void registerNewLibraryUser(LibraryUser libraryUser){

    }
}
