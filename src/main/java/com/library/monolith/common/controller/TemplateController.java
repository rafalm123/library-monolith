//package com.library.monolith.common.controller;
//
//import com.library.monolith.common.model.dto.book.BookCreateDTO;
//import com.library.monolith.common.model.dto.book.BookOverviewDTO;
//import com.library.monolith.common.service.book.BookServiceImplementation;
//import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
//import com.library.monolith.common.model.dto.user.LibraryUserRegistrationDTO;
//import com.library.monolith.common.service.user.UserServiceImplementation;
//import com.library.monolith.common.repository.user.LibraryUserRepository;
//import com.library.monolith.common.repository.user.RoleRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@AllArgsConstructor
//@RequestMapping("/")
//public class TemplateController {
//
//    private final LibraryUserRepository libraryUserRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final BookServiceImplementation bookServiceImplementation;
//    private final UserServiceImplementation userServiceImplementation;
//    private final RoleRepository roleRepository;
//
////    @GetMapping("auth/login")
////    public String getLogin(@RequestParam(value = "error", required = false) String error, Model model) {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        if (!(authentication instanceof AnonymousAuthenticationToken)) {
////            return "redirect:/logout_first";
////        }
////        if (error != null) {
////            model.addAttribute("error", "Invalid username or password.");
////        }
////        return "auth_login";
////    }
//
//    @GetMapping("/home")
//    public String getHome(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentNickname = authentication.getName();
//        model.addAttribute("nickname", currentNickname);
//        return "home";
//    }
//
//    @GetMapping("/")
//    public String getIndex() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            return "redirect:/logout_first";
//        }
//        return "index";
//    }
//
//    @GetMapping("/list_users")
//    public String listUsers(Model model) {
//        List<LibraryUserOverviewDTO> users = userServiceImplementation.getLibraryUserOverviewDtoList();
//        model.addAttribute("users", users);
//        return "list_users";
//    }
//
//    @GetMapping("/list_books")
//    public String getListBooks(Model model) {
//        List<BookOverviewDTO> books = bookServiceImplementation.getBookOverviewDtoList();
//        model.addAttribute("books", books);
//        return "list_books";
//    }
//
//    @GetMapping("/register")
//    public String showSignUpForm(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            return "redirect:/logout_first";
//        }
//
//        model.addAttribute("user", new LibraryUserRegistrationDTO());
//        return "signup";
//    }
//
//    @PostMapping("/process_register")
//    public String processRegistration(@ModelAttribute("user") LibraryUserRegistrationDTO libraryUserRegistrationDTO) {
//        userServiceImplementation.registerUser(libraryUserRegistrationDTO);
//        return "register_success";
//    }
//
//    @GetMapping("/add_book")
//    public String showAddBookForm(Model model) {
//        model.addAttribute("bookCreateDTO", new BookCreateDTO());
//        return "add_book_form";
//    }
//
//    @PostMapping("/process_book")
//    public String processBook(@ModelAttribute("bookCreateDTO") BookCreateDTO bookCreateDTO) {
//        bookServiceImplementation.addBook(bookCreateDTO);
//        return "process_book_success";
//    }
//
//    @GetMapping("logout_redirect")
//    public String getLogoutFirst() {
//        return "logout_redirect";
//    }
//
//    private static boolean isAuthenticated() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
//    }
//}
//
