package com.example.demo;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runner(BookService bookService){
//        return args -> {
//
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>(){};
//            InputStream inputStream = TypeReference.class.getResourceAsStream("/static/books.json");
//            try {
//
//                List<Book> books = mapper.readValue(inputStream, typeReference);
//                bookService.saveList(books);
//                System.out.println("Saved");
//            } catch (IOException e){
//                System.out.printf("Error: " + e.getMessage());
//            }
//        };
//    }
}
