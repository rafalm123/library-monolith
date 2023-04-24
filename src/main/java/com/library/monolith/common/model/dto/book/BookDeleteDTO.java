package com.library.monolith.common.model.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookDeleteDTO {

    private String title;
    private String author;
}
