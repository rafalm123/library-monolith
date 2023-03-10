package com.library.monolith.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name="book_release")
public class BookReleaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="isbn")
    private Long isbn;
    @Column(
            name = "book_release_uuid",
            unique = true)
    @GeneratedValue(generator = "UUID")
    private UUID bookReleaseUuid;
    @Column(name="pages")
    private Long pages;
    @Column(name="release_year")
    private Long publisherReleaseYear;
    @Column(name="language")
    private String language;
    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private BookEntity book;
    @OneToMany(mappedBy = "bookRelease",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReleaseCopyEntity> copies;


}
