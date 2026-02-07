package com.abacus.franchise.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class KitOrderItem {

	@Id
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "order_item_id", length = 36, updatable = false, nullable = false)
	private UUID orderItemId;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "course_id", length = 36)
    private UUID courseId;

    @Column(name = "kit_count")
    private int kitCount;
    
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "kit_request_id", length = 36, updatable = false, nullable = false)
    private UUID kitRequestId;

	public UUID getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(UUID orderItemId) {
		this.orderItemId = orderItemId;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public int getKitCount() {
		return kitCount;
	}

	public void setKitCount(int kitCount) {
		this.kitCount = kitCount;
	}

	public UUID getKitRequestId() {
		return kitRequestId;
	}

	public void setKitRequestId(UUID kitRequestId) {
		this.kitRequestId = kitRequestId;
	}

}

