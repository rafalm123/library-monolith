package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "book")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    @Column(
            name = "book_uuid",
            unique = true
    )
    @GeneratedValue(generator = "UUID")
    private UUID bookUuid;
    @Column(name="author")
    private String author;
    @Column(name="title")
    private String title;
    @Column(name = "publish_year")
    private Long bookPublicationYear;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BookReleaseEntity> releases = new ArrayList<>();
}
