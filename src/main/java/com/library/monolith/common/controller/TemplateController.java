package com.library.monolith.common.controller;

import com.library.monolith.common.mapping.user.LibraryUserRegistrationDtoMapper;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.dto.book.BookServiceImplementation;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.dto.user.LibraryUserRegistrationDTO;
import com.library.monolith.common.model.dto.user.UserServiceImplementation;
import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class TemplateController {

    private final LibraryUserRepository libraryUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookServiceImplementation bookServiceImplementation;
    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public TemplateController(LibraryUserRepository libraryUserRepository,
                              PasswordEncoder passwordEncoder,
                              BookServiceImplementation bookServiceImplementation,
                              UserServiceImplementation userServiceImplementation) {
        this.libraryUserRepository = libraryUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookServiceImplementation = bookServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired


    @GetMapping("/login")
    public String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/logout_redirect";
        }
        return "login";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentNickname = authentication.getName();
        model.addAttribute("nickname", currentNickname);
        return "home";
    }

    @GetMapping("/")
    public String getIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/logout_redirect";
        }
        return "index";
    }

    @GetMapping("/list_users")
    public String listUsers(Model model) {
        List<LibraryUserOverviewDTO> users = userServiceImplementation.getLibraryUserOverviewDtoList();
        model.addAttribute("users", users);
        return "list_users";
    }

    @GetMapping("/list_books")
    public String getListBooks(Model model) {
        List<BookOverviewDTO> books = bookServiceImplementation.getBookOverviewDtoList();
        model.addAttribute("books", books);
        return "list_books";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/logout_redirect";
        }

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

    @GetMapping("logout_redirect")
    public String getLogoutFirst() {
        return "logout_redirect";
    }

    private static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
