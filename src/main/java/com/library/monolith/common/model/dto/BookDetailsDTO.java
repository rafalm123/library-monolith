package com.library.monolith.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {

    private String author;
    private String title;
    private Integer bookYear;
    private Integer pages;
    private Long isbn;
    private Integer releaseYear;
    private String language;

}
