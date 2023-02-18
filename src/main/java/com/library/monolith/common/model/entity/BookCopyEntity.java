package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class BookCopyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long bookId;
    @GeneratedValue(generator = "UUID")
    private UUID bookCopyUuid;
    private String copyLanguage;
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_entity_id", referencedColumnName = "id")
    private BookEntity bookByBookIdEntity;
    @OneToMany(mappedBy = "bookCopyByBookCopyIdEntity")
    private List<BookCopyVersionEntity> bookCopyVersion;
    @ManyToMany(mappedBy = "copies")
    private List<UserBookRegistryEntity> registry;
}
