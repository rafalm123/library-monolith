package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "release_copy_version")
public class ReleaseCopyVersion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="start_validity")
    private Timestamp startValidity;
    @Column(name="end_validity")
    private Timestamp endValidity;
    @Column(name="status")
    private String status;
    @Column(name="notes")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "release_copy_id", referencedColumnName = "id")
    private ReleaseCopy releaseCopy;
}
