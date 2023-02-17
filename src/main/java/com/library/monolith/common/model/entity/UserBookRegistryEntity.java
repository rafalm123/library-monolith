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
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "user_book_registry", schema = "library", catalog = "library")
public class UserBookRegistryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_book_registry_book_copy",
            joinColumns = @JoinColumn(name = "user_book_registry_id"),
            inverseJoinColumns = @JoinColumn(name = "book_copy_id")
    )
    private List<BookCopyEntity> copies = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_book_registry_library_user",
            joinColumns = @JoinColumn(name = "user_book_registry_id"),
            inverseJoinColumns = @JoinColumn(name = "library_user_id")
    )
    private List<LibraryUserEntity> users = new ArrayList<>();
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "registry_uuid", nullable = false)
    private UUID registryUuid;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "finish_at", nullable = false)
    private Timestamp finishAt;
}
