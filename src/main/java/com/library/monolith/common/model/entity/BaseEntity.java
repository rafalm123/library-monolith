package com.library.monolith.common.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_id_seq_generator")
    @SequenceGenerator(name = "book_id_seq_generator", sequenceName = "book_id_seq", allocationSize = 1)
    private Long id;

}
