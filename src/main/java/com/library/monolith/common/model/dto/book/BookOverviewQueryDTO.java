package com.library.monolith.common.model.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOverviewQueryDTO {
    String title;
    String author;
    int page;
    int pageSize;
}
