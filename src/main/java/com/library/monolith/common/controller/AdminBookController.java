package com.library.monolith.common.controller;

import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.entity.user.LibraryUser;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/library/books")
public class AdminBookController {

    private static final List<LibraryUserOverviewDTO> libraryUserOverviewDTOS = Arrays.asList(
            new LibraryUserOverviewDTO("name1","surname1", 111L,"email1@gmail.com"),
            new LibraryUserOverviewDTO("name2","surname2", 222L,"email2@gmail.com"),
            new LibraryUserOverviewDTO("name3","surname3", 333L,"email3@gmail.com")
    );

    @PostMapping
    public void registerNewLibraryUser(@RequestBody LibraryUser libraryUser){

    }
}
