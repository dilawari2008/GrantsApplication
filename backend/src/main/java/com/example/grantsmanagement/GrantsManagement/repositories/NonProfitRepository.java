package com.example.grantsmanagement.GrantsManagement.repositories;

import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NonProfitRepository extends JpaRepository<NonProfit, Long> {
    @Query(value = "select c from NonProfit c where c.foundationId = ?1")
    Page<NonProfit> findAllNonProfitsByFoundationId(Long foundationId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update NonProfit c set c.template = ?2 where c.id = ?1")
    void updateTemplate(Long nonProfitId, String template);
}
