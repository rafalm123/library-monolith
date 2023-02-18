package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long isbn;
    @GeneratedValue(generator = "UUID")
    private UUID bookUuid;
    private String author;
    private String title;
    private Long pages;
    private Long releaseYear;
    @OneToMany(mappedBy = "bookByBookIdEntity")
    private Collection<BookCopyEntity> bookCopiesById;
}
