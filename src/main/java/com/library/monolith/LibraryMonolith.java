package com.library.monolith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.monolith.common.model.entity.EntityMapper;
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
    public EntityMapper entityMapperMain(BookRepository bookRepository,ObjectMapper objectMapper){
        return new EntityMapper(bookRepository,objectMapper);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibraryMonolith.class, args);
        EntityMapper mapper = context.getBean(EntityMapper.class);
        try {
            mapper.saveBooksFromJson();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
