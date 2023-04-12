package com.library.monolith.common.controller;

import com.library.monolith.common.mapping.user.LibraryUserRegistrationDtoMapper;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.dto.book.BookServiceImplementation;
import com.library.monolith.common.model.dto.user.LibraryUserRegistrationDTO;
import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TemplateController {

    private LibraryUserRepository libraryUserRepository;
    private PasswordEncoder passwordEncoder;
    private final BookServiceImplementation bookServiceImplementation;

    @Autowired
    public TemplateController(LibraryUserRepository libraryUserRepository,
                              PasswordEncoder passwordEncoder,
                              BookServiceImplementation bookServiceImplementation) {
        this.libraryUserRepository = libraryUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("home")
    public String getHome() {
        return "home";
    }

    @GetMapping("")
    public String getIndex() {
        return "index";
    }

    @GetMapping("list_books")
    public String getListBooks(Model model) {
        List<BookOverviewDTO> books = bookServiceImplementation.getBookOverviewDtoList();
        model.addAttribute("books", books);
        return "list_books";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new LibraryUserRegistrationDTO());
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegistration(@ModelAttribute("user") LibraryUserRegistrationDTO libraryUserRegistrationDTO) {
        LibraryUser libraryUser = LibraryUserRegistrationDtoMapper.getInstance().toLibraryUser(libraryUserRegistrationDTO);
        LibraryUserVersion libraryUserVersion = LibraryUserRegistrationDtoMapper.getInstance().toLibraryUserVersion(libraryUserRegistrationDTO);
        Address address = LibraryUserRegistrationDtoMapper.getInstance().toAddress(libraryUserRegistrationDTO);

        libraryUser.setPassword(passwordEncoder.encode(libraryUser.getPassword()));

        libraryUserVersion.setLibraryUser(libraryUser);
        libraryUserVersion.setAddress(address);
        address.setLibraryUserVersion(libraryUserVersion);

        libraryUser.setLibraryUserVersions(Collections.singletonList(libraryUserVersion));

        libraryUserRepository.save(libraryUser);

        return "register_success";
    }
}

