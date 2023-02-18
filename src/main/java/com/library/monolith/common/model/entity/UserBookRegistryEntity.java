package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserBookRegistryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_book_registry_entity_book_copy_entity",
            joinColumns = {@JoinColumn(name = "user_book_registry_entity_id")},
            inverseJoinColumns = @JoinColumn(name = "book_copy_entity_id")
    )
    private List<BookCopyEntity> copies = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_book_registry_entity_library_user_entity",
            joinColumns = @JoinColumn(name = "user_book_registry_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "library_user_entity_id")
    )
    private List<LibraryUserEntity> users = new ArrayList<>();

    private UUID registryUuid;
    private Timestamp createdAt;
    private Timestamp finishAt;
}
