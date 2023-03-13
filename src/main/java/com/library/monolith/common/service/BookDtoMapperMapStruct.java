package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookDtoMapperMapStruct {

@Mapping(source = "book.author",target = "author")
@Mapping(source = "book.title",target = "title")
@Mapping(source = "book.bookPublicationYear",target = "bookPublicationYear")
@Mapping(source = "release.pages",target = "pages")
@Mapping(source = "release.isbn",target = "isbn")
@Mapping(source = "release.publisherReleaseYear",target = "publisherReleaseYear")
@Mapping(source = "release.language",target = "language")
BookDetailsDTO dtoMapping(BookEntity book,BookReleaseEntity release);

}
