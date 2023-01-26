package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {

    List<Book> findBookByAuthor(String author);

}
