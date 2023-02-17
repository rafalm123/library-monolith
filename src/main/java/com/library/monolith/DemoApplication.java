package com.library.monolith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.monolith.common.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
