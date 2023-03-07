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
public class BookReleaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long isbn;
    @Column(unique = true)
    @GeneratedValue(generator = "UUID")
    private UUID bookReleaseUuid;
    private Long pages;
    private Long publisherReleaseYear;
    private String language;
    @ManyToOne
    @JoinColumn(name = "book_entity_id",referencedColumnName = "id")
    private BookEntity bookById;
    @OneToMany
    private List<ReleaseCopyEntity> copies;


}
