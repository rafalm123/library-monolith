package com.library.monolith.common.mapping;

import com.library.monolith.common.model.dto.BookDtoOverview;
import com.library.monolith.common.model.entity.Book;
import com.library.monolith.common.model.entity.BookRelease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BookOverviewDtoMapper {
    @Mapping(source = "book.author",target = "author")
    @Mapping(source = "book.title",target = "title")
    @Mapping(source = "bookRelease.language",target = "language")
    BookDtoOverview toBookOverviewDto(Book book, BookRelease bookRelease);
}
