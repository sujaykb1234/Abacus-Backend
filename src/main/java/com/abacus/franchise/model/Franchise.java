package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private long franchiseId;

    @Column(name = "creation_time", length = 255)
    private String creationTime;

    @Column(name = "document_image_link", length = 255)
    private String documentImageLink;

    @Column(name = "document_image_name", length = 255)
    private String documentImageName;

    @Column(name = "document_number", length = 255)
    private String documentNumber;

    @Column(name = "document_type", length = 255)
    private String documentType;

    @Column(name = "franchise_status")
    private Byte franchiseStatus;

    @Column(name = "franchise_email", length = 255)
    private String franchiseEmail;

    @Column(name = "franchise_name", length = 255)
    private String franchiseName;

    @Column(name = "franchise_number", length = 255)
    private String franchiseNumber;

    @Column(name = "franchise_owner", length = 255)
    private String franchiseOwner;

    @Column(name = "franchise_password", length = 255)
    private String franchisePassword;

    @Column(name = "gender", length = 255)
    private String gender;

    @Column(name = "mobile_no", length = 255)
    private String mobileNo;

    @Column(name = "modification_time", length = 255)
    private String modificationTime;

    @Column(name = "owner_dob", length = 255)
    private String ownerDob;

    @Column(name = "profile_image_link", length = 255)
    private String profileImageLink;

    @Column(name = "profile_image_name", length = 255)
    private String profileImageName;

    @Column(name = "roles")
    private Byte roles;

    @Column(name = "franchise_address_address_id", length = 36, unique = true)
    private String franchiseAddressAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_address_address_id", insertable = false, updatable = false)
    private Address address;
}
