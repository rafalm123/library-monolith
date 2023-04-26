package com.library.monolith.common.model.entity.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "entiy_id_seq", sequenceName = "entiy_id_seq", allocationSize = 10)
public class ReleaseCopyVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entiy_id_seq")
    private Long id;

    @Column(name = "start_validity")
    private Timestamp startValidity = Timestamp.from(Instant.now());
    @Column(name = "end_validity")
    private Timestamp endValidity = null;
    @Column(name = "status")
    private String status;
    @Column(name = "notes")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "release_copy_id", referencedColumnName = "id")
    private ReleaseCopy releaseCopy;
}
