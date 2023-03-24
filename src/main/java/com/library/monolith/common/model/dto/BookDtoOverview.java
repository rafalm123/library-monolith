package com.library.monolith.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDtoOverview {

    private String author;
    private String title;
    private String language;

}
