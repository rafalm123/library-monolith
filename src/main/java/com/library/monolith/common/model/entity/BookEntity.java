package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    private UUID bookUuid;
    private String author;
    private String title;
    private Long bookPublicationYear;
    @OneToMany(mappedBy = "bookById")
    private List<BookReleaseEntity> releases;
}
