package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LibraryUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private UUID userUuid;
    private Timestamp creationTime;
    private String userType;

    @ManyToMany(mappedBy = "users")
    private List<UserBookRegistryEntity> registry;
    @OneToMany(mappedBy = "libraryUserByUserIdEntity")
    private List<LibraryUserVersionEntity> version;

}
