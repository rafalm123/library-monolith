package com.library.monolith.common.model.entity.book;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "release_copy")
public class ReleaseCopy extends BaseEntity {

    @Column(name="cover_type")
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_release_id",referencedColumnName = "id")
    private BookRelease bookRelease;
    @OneToMany(mappedBy = "releaseCopy",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReleaseCopyVersion> releaseCopyVersions;
}
