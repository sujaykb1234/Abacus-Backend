package com.abacus.franchise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stored_images")
public class StoredImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stored_image_id")
    private Long storedImageId;

    @Column(name = "profile_image_name", nullable = false)
    private String profileImageName;

    @Column(name = "profile_image_link", nullable = false)
    private String profileImageLink;

    @Column(name = "id_proof_image_name")
    private String idProofImageName;

    @Column(name = "id_proof_image_link")
    private String idProofImageLink;

    @Column(name = "pdf_name")
    private String pdfName;

    @Column(name = "pdf_link")
    private String pdfLink;

    public StoredImages() {
    }

    public StoredImages(Long storedImageId, String profileImageName, String profileImageLink,
                        String idProofImageName, String idProofImageLink, String pdfName, String pdfLink) {
        this.storedImageId = storedImageId;
        this.profileImageName = profileImageName;
        this.profileImageLink = profileImageLink;
        this.idProofImageName = idProofImageName;
        this.idProofImageLink = idProofImageLink;
        this.pdfName = pdfName;
        this.pdfLink = pdfLink;
    }

    // Getters and setters
    public Long getStoredImageId() {
        return storedImageId;
    }

    public void setStoredImageId(Long storedImageId) {
        this.storedImageId = storedImageId;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public String getIdProofImageName() {
        return idProofImageName;
    }

    public void setIdProofImageName(String idProofImageName) {
        this.idProofImageName = idProofImageName;
    }

    public String getIdProofImageLink() {
        return idProofImageLink;
    }

    public void setIdProofImageLink(String idProofImageLink) {
        this.idProofImageLink = idProofImageLink;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }
}
