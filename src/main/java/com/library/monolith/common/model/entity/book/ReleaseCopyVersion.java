package com.library.monolith.common.model.entity.book;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "release_copy_version")
public class ReleaseCopyVersion extends BaseEntity {

    @Column(name="start_validity")
    private Timestamp startValidity = Timestamp.from(Instant.now());
    @Column(name="end_validity")
    private Timestamp endValidity = null;
    @Column(name="status")
    private String status;
    @Column(name="notes")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "release_copy_id", referencedColumnName = "id")
    private ReleaseCopy releaseCopy;
}
