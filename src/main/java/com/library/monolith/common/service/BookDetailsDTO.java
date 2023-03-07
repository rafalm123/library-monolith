package com.library.monolith.common.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {

    private String author;
    private String title;
    private Long bookPublicationYear;
    private Long pages;
    private Long isbn;
    private Long publisherReleaseYear;
    private String language;

}
