package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Optional<BookDto> getBookByUuid(UUID uuid) {

        //tutaj zrób konwersję jednej lub więcej encji (w zależności od potrzeb) na obiekt BookDto
        //zrobić do tego osobną klasę- konwerter której zapodasz encję lub encje i która zwróci DTO
        //jeśli będzie użyta tylko jedna encja, użyj mapstructa

        val book = bookRepository.findBookByBookUuid(uuid);
        if(book.isEmpty()) {
            reponse entity no content
        };


        ResponseEntity.ok(book.get());

    }
}
