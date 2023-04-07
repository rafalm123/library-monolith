package com.library.monolith.common.mapping.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.monolith.common.model.entity.book.Book;
import lombok.Data;

import java.util.List;

@Data
public class BooksWrapper {

    @JsonProperty("book")
    private List<Book> books;

}
