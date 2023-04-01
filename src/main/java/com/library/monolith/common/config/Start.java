//package com.library.monolith.common.config;
//
//import com.library.monolith.common.model.entity.user.LibraryUser;
//import com.library.monolith.common.repository.user.LibraryUserRepository;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//@Data
//@Component
//public class Start {
//
//    private PasswordEncoder passwordEncoder;
//
//    private LibraryUserRepository libraryUserRepository;
//
//    public Start(PasswordEncoder passwordEncoder, LibraryUserRepository libraryUserRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.libraryUserRepository = libraryUserRepository;
//
//        LibraryUser libraryUser = new LibraryUser();
//        libraryUser.setUsername("Przemek");
//        libraryUser.setPassword(passwordEncoder.encode("123"));
//        libraryUserRepository.save(libraryUser);
//    }
//}
