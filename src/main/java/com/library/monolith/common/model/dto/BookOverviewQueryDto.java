package com.library.monolith.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOverviewQueryDto {
    String title;
    String author;
    int page;
    int pageSize;
}
