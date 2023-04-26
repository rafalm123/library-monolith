package com.library.monolith.common.model.entity.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "entiy_id_seq", sequenceName = "entiy_id_seq", allocationSize = 10)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entiy_id_seq")
    private Long id;

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
    @JoinColumn(name = "library_user_version_id", referencedColumnName = "id")
    private LibraryUserVersion libraryUserVersion;

    public Address(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
}

