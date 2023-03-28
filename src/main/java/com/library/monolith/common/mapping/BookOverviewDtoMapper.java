package com.library.monolith.common.mapping;

import com.library.monolith.common.model.dto.BookOverviewDTO;
import com.library.monolith.common.model.entity.Book;
import com.library.monolith.common.model.entity.BookRelease;
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
