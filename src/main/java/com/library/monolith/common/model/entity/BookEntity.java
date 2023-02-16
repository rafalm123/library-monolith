package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "book", schema = "library", catalog = "library")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "isbn", nullable = false)
    private Long isbn;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "book_uuid", nullable = false)
    private UUID bookUuid;
    @Basic
    @Column(name = "author", nullable = false, length = 50)
    private String author;
    @Basic
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Basic
    @Column(name = "pages", nullable = false)
    private Long pages;
    @Basic
    @Column(name = "release_year", nullable = false)
    private Long releaseYear;
    @OneToMany(mappedBy = "bookByBookId")
    private Collection<BookCopyEntity> bookCopiesById;
}
