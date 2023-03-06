package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String author;
    private String title;
    private Long pages;
    private Long releaseYear;
    private List<ReleaseCopyEntity> copies;
    
}
