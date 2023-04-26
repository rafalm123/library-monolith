package com.library.monolith.common.model.entity.book;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "entiy_id_seq", sequenceName = "entiy_id_seq", allocationSize = 10)
public class ReleaseCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entiy_id_seq")
    private Long id;

    @Column(name = "cover_type")
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_release_id", referencedColumnName = "id")
    private BookRelease bookRelease;
    @OneToMany(mappedBy = "releaseCopy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReleaseCopyVersion> releaseCopyVersions;
}
