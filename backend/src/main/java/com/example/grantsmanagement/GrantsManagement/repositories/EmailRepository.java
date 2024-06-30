package com.example.grantsmanagement.GrantsManagement.repositories;

import com.example.grantsmanagement.GrantsManagement.models.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query("select e from Email e where e.foundationId = ?1")
    Page<Email> findAllEmailsByFoundationId(Long foundationId, Pageable pageable);

    @Query("select e from Email e where e.foundationId = ?1 and e.nonProfitId = ?2")
    Page<Email> findAllEmailsByFoundationIdNonProfitId(Long foundationId, Long nonProfitId, Pageable pageable);
}
