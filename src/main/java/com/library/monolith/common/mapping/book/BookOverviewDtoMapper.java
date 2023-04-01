package com.library.monolith.common.mapping.book;

import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface BookOverviewDtoMapper {
    static BookOverviewDtoMapper getInstance() {
        return Mappers.getMapper(BookOverviewDtoMapper.class);
    }

    @Mapping(source = "book.author",target = "author")
    @Mapping(source = "book.title",target = "title")
    @Mapping(source = "bookRelease.language",target = "language")
    BookOverviewDTO toBookOverviewDto(Book book, BookRelease bookRelease);
}
