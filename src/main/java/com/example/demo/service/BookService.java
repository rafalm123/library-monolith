package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.Arrays.stream;

@Service
public class BookService implements BookServiceI{

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> listBooks(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Iterable<Book> saveList(List<Book> listOfBooks){
        return bookRepository.saveAll(listOfBooks);
    }

    //nie wiem czy tworzyc tego typu metody dla kazdej zmiennej, czy wybrac jakies najbardziej uzyteczne typu naziwsko, tytuł itd
    // + fajnie gdyby przyszlosciowo dodac feature na froncie, ze jesli ktos wpisuje np literke P to wyswietla mu proponowane na ta litere ale
    // to wiuadomo nie od razu Rzym itd
    public Optional<Book> getById(long id){
        return StreamSupport.stream(listBooks().spliterator(),false)
                .filter(book -> book.getId()==id)
                .findFirst();
    }


}
