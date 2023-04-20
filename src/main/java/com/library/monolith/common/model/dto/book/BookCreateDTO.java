package com.library.monolith.common.model.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDTO {

    private String author;
    private String title;
    private Integer publishYear;
    private Long isbn;
    private Integer pages;
    private Integer releaseYear;
    private String language;
    private String coverType;
    private String status;
    private String notes;

}