package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class ReleaseCopyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long bookId;
    @GeneratedValue(generator = "UUID")
    private UUID bookCopyUuid;
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_release_entity_id",referencedColumnName = "id")
    private BookReleaseEntity bookReleaseById;
    @OneToMany
    private List<ReleaseCopyVersionEntity> versions;
}
