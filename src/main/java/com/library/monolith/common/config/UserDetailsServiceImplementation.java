//package com.library.monolith.common.config;
//
//import com.library.monolith.common.repository.user.LibraryUserRepository;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Primary
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Service
//public class UserDetailsServiceImplementation implements UserDetailsService {
//
//    private LibraryUserRepository libraryUserRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return libraryUserRepository.findAllByUsername(username);
//    }
//}
