package com.library.monolith.common.model.entity.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BooksWrapper {

    @JsonProperty("book")
    private List<Book> books;

}
