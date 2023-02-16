package com.example.demo.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "library_user_version", schema = "library", catalog = "library")
public class LibraryUserVersion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "user_id", nullable = true, insertable = false, updatable = false)
    private Long userId;
    @Basic
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Basic
    @Column(name = "postal_code", nullable = false, length = 50)
    private String postalCode;
    @Basic
    @Column(name = "debt", nullable = true)
    private BigDecimal debt;
    @Basic
    @Column(name = "start_validity", nullable = false)
    private Timestamp startValidity;
    @Basic
    @Column(name = "end_validity", nullable = true)
    private Timestamp endValidity;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private LibraryUser libraryUserByUserId;

    public LibraryUserVersion() {
    }

    public LibraryUserVersion(String nickname, String email, String address, String postalCode, Timestamp startValidity, Timestamp endValidity) {
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.startValidity = startValidity;
        this.endValidity = endValidity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public Timestamp getStartValidity() {
        return startValidity;
    }

    public void setStartValidity(Timestamp startValidity) {
        this.startValidity = startValidity;
    }

    public Timestamp getEndValidity() {
        return endValidity;
    }

    public void setEndValidity(Timestamp endValidity) {
        this.endValidity = endValidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUserVersion that = (LibraryUserVersion) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(nickname, that.nickname)
                && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(postalCode, that.postalCode) && Objects.equals(debt, that.debt)
                && Objects.equals(startValidity, that.startValidity) && Objects.equals(endValidity, that.endValidity) && Objects.equals(libraryUserByUserId, that.libraryUserByUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, nickname, email, address, postalCode, debt, startValidity, endValidity, libraryUserByUserId);
    }

    public LibraryUser getLibraryUserByUserId() {
        return libraryUserByUserId;
    }

    public void setLibraryUserByUserId(LibraryUser libraryUserByUserId) {
        this.libraryUserByUserId = libraryUserByUserId;
    }
}
