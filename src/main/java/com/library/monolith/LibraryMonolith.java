package com.library.monolith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.monolith.common.mapping.book.BookEntityMapper;
import com.library.monolith.common.repository.book.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class LibraryMonolith {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public BookEntityMapper entityMapperMain(BookRepository bookRepository, ObjectMapper objectMapper){
        return new BookEntityMapper(bookRepository,objectMapper);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibraryMonolith.class, args);
        BookEntityMapper mapper = context.getBean(BookEntityMapper.class);
        try {
            mapper.saveBooksFromJson();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
