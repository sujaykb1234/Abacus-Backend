package com.abacus.franchise.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.KitRequests;

@Repository
public interface KitRequestsRepository extends JpaRepository<KitRequests, UUID> {

}
