package com.library.monolith.common.model.entity.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "entiy_id_seq", sequenceName = "entiy_id_seq", allocationSize = 10)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entiy_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

}
