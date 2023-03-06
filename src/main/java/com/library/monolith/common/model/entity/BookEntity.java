package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
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
    @Column(unique = true)
    @GeneratedValue(generator = "UUID")
    private UUID book_uuid;
    private String author;
    private String title;
    private Long publish_year;
    @OneToMany(mappedBy = "book")
    private List<BookReleaseEntity> bookReleases;
}
