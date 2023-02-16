package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

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

}
