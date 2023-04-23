package com.library.monolith.common.mapping.book;

import com.library.monolith.common.model.dto.book.BookCreateDTO;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import com.library.monolith.common.model.entity.book.ReleaseCopy;
import com.library.monolith.common.model.entity.book.ReleaseCopyVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookCreateDtoMapper {
    static BookCreateDtoMapper getInstance = Mappers.getMapper(BookCreateDtoMapper.class);

    @Mapping(source = "book.author", target = "author")
    @Mapping(source = "book.title", target = "title")
    @Mapping(source = "book.publishYear", target = "publishYear")
    @Mapping(source = "bookRelease.pages", target = "pages")
    @Mapping(source = "bookRelease.isbn", target = "isbn")
    @Mapping(source = "bookRelease.releaseYear", target = "releaseYear")
    @Mapping(source = "bookRelease.language", target = "language")
    @Mapping(source = "releaseCopy.coverType", target = "coverType")
    @Mapping(source = "releaseCopyVersion.status", target = "status")
    @Mapping(source = "releaseCopyVersion.notes", target = "notes")
    BookCreateDTO toBookCreateDto(Book book,
                                   BookRelease bookRelease,
                                   ReleaseCopy releaseCopy,
                                   ReleaseCopyVersion releaseCopyVersion);


    Book toBook(BookCreateDTO bookCreateDTO);

    BookRelease toBookRelease(BookCreateDTO bookCreateDTO, Book book);

    ReleaseCopy toReleaseCopy(BookCreateDTO bookCreateDTO, BookRelease bookRelease);

    ReleaseCopyVersion toReleaseCopyVersion(BookCreateDTO bookCreateDTO, ReleaseCopy releaseCopy);
}