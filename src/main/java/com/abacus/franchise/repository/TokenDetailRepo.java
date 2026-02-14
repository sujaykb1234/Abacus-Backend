package com.abacus.franchise.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.abacus.franchise.model.TokenDetail;

@Repository
public interface TokenDetailRepo extends JpaRepository<TokenDetail, UUID> {

}
