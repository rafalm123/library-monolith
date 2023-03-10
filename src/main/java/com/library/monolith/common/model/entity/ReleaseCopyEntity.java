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
@Table(name = "release_copy")
public class ReleaseCopyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    @GeneratedValue(generator = "UUID")
    @Column(name="release_copy_uuid")
    private UUID bookCopyUuid;
    @Column(name="cover_type")
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_release_id",referencedColumnName = "id")
    private BookReleaseEntity bookRelease;
    @OneToMany(mappedBy = "releaseCopy",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReleaseCopyVersionEntity> versions;
}
