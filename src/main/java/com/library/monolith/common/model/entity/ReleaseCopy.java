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
public class ReleaseCopy extends BaseEntity{

    @Column(name="cover_type")
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_release_id",referencedColumnName = "id")
    private BookRelease bookRelease;
    @OneToMany(mappedBy = "releaseCopy",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReleaseCopyVersion> versions;
}
