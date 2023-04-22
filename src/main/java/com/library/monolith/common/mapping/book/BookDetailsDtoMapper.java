package com.library.monolith.common.mapping.book;

import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookDetailsDtoMapper {
    static BookDetailsDtoMapper getInstance() {
        return Mappers.getMapper(BookDetailsDtoMapper.class);
    }

    @Mapping(source = "book.author", target = "author")
    @Mapping(source = "book.title", target = "title")
    @Mapping(source = "book.publishYear", target = "bookYear")
    @Mapping(source = "bookRelease.pages", target = "pages")
    @Mapping(source = "bookRelease.isbn", target = "isbn")
    @Mapping(source = "bookRelease.releaseYear", target = "releaseYear")
    @Mapping(source = "bookRelease.language", target = "language")
    BookDetailsDTO toBookDetailsDto(Book book,
                                    BookRelease bookRelease);

}
