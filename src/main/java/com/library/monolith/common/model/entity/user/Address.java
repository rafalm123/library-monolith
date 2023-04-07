package com.library.monolith.common.model.entity.user;


import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @OneToOne
    @JoinColumn(name = "library_user_version_id",referencedColumnName = "id")
    private LibraryUserVersion libraryUserVersion;

}

