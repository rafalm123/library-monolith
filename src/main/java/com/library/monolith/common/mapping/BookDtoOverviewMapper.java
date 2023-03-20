package com.library.monolith.common.mapping;

import com.library.monolith.common.model.dto.BookDtoOverview;
import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper

public interface BookDtoOverviewMapper {

    @Mapping(source = "book.author",target = "author")
    @Mapping(source = "book.title",target = "title")
    @Mapping(source = "release.language",target = "language")
    BookDtoOverview toBookOverviewDto(BookEntity book, BookReleaseEntity release);
}
