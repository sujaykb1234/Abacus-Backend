package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StoredImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long Stroredimage_id;
	private String profile_image_name;
	private String profile_image_link;
	private String id_proof_image_name;
	private String id_proof_image_link;

	private String pdfName;
	private String pdfLink;

	public Long getStroredimage_id() {
		return Stroredimage_id;
	}

	public void setStroredimage_id(Long stroredimage_id) {
		Stroredimage_id = stroredimage_id;
	}

	public String getProfile_image_name() {
		return profile_image_name;
	}

	public void setProfile_image_name(String profile_image_name) {
		this.profile_image_name = profile_image_name;
	}

	public String getProfile_image_link() {
		return profile_image_link;
	}

	public void setProfile_image_link(String profile_image_link) {
		this.profile_image_link = profile_image_link;
	}

	public String getId_proof_image_name() {
		return id_proof_image_name;
	}

	public void setId_proof_image_name(String id_proof_image_name) {
		this.id_proof_image_name = id_proof_image_name;
	}

	public String getId_proof_image_link() {
		return id_proof_image_link;
	}

	public void setId_proof_image_link(String id_proof_image_link) {
		this.id_proof_image_link = id_proof_image_link;
	}

	public StoredImages(Long stroredimage_id, String profile_image_name, String profile_image_link,
			String id_proof_image_name, String id_proof_image_link, String pdfName, String pdfLink) {
		super();
		Stroredimage_id = stroredimage_id;
		this.profile_image_name = profile_image_name;
		this.profile_image_link = profile_image_link;
		this.id_proof_image_name = id_proof_image_name;
		this.id_proof_image_link = id_proof_image_link;
		this.pdfName = pdfName;
		this.pdfLink = pdfLink;
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

	public StoredImages() {
		super();
		// TODO Auto-generated constructor stub
	}

}