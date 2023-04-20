package com.library.monolith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.monolith.common.mapping.book.BookEntityMapper;
import com.library.monolith.common.mapping.user.UserEntityMapper;
import com.library.monolith.common.repository.book.BookRepository;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class LibraryMonolith {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public BookEntityMapper BookEntityMapperMain(BookRepository bookRepository, ObjectMapper objectMapper){
        return new BookEntityMapper(bookRepository,objectMapper);
    }

    @Bean
    public UserEntityMapper UserEntityMapperMain(LibraryUserRepository libraryUserRepository,
                                             ObjectMapper objectMapper,
                                             PasswordEncoder passwordEncoder)
    {
        return new UserEntityMapper(libraryUserRepository,objectMapper,passwordEncoder);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibraryMonolith.class, args);
        BookEntityMapper bookMapper = context.getBean(BookEntityMapper.class);
//        UserEntityMapper userMapper = context.getBean(UserEntityMapper.class);
        try {
            bookMapper.saveBooksFromJson();
//            userMapper.saveUserFromJson();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
