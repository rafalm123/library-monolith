package com.library.monolith.common.model.dto;

import com.library.monolith.common.model.entity.BookReleaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

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
