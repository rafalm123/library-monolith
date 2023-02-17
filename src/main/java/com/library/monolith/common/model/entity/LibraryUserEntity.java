package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

//powtórzenie listy registry w bookcopyentity i libraryuser entity - jakas nowa klasa?

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "library_user", schema = "library", catalog = "library")
public class LibraryUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;
    @Basic
    @Column(name = "creation_time", nullable = false)
    private Timestamp creationTime;
    @Basic
    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;
    @ManyToMany(mappedBy = "users")
    private List<UserBookRegistryEntity> registry;
    @OneToMany(mappedBy = "libraryUserByUserIdEntity")
    private List<LibraryUserVersionEntity> version;
}
