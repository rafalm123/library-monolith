package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role extends BaseEntity{

    @Column(name = "name")
    private String name;

}
