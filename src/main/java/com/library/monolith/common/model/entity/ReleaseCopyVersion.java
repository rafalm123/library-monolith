package com.library.monolith.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "release_copy_version")
public class ReleaseCopyVersion extends BaseEntity{

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
