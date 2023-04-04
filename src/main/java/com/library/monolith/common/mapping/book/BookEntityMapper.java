package com.library.monolith.common.mapping.book;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BooksWrapper;
import com.library.monolith.common.repository.book.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntityMapper {

    private BookRepository bookRepository;
    private ObjectMapper mapper;

    public void saveBooksFromJson() throws IOException {

        try (InputStream inputStream = new FileInputStream(new File("C:\\Users\\Dell\\IdeaProjects\\library\\src\\main\\resources\\static\\booksai.json"))) {
            BooksWrapper booksWrapper = mapper.readValue(
                    inputStream,
                    BooksWrapper.class);

            List<Book> books = booksWrapper.getBooks();

            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            books.forEach(book -> book.getBookReleases()
                    .forEach(bookRelease -> {
                        bookRelease.setBook(book);
                        bookRelease.getReleaseCopies().
                                forEach(releaseCopy -> {
                                    releaseCopy.setBookRelease(bookRelease);
                                    releaseCopy.getReleaseCopyVersions()
                                            .forEach(releaseCopyVersion -> releaseCopyVersion.setReleaseCopy(releaseCopy));
                                });
                    })
            );
            bookRepository.saveAll(books);
        }
    }
}

