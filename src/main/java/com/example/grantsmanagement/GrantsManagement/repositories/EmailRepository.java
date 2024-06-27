package com.example.grantsmanagement.GrantsManagement.repositories;

import com.example.grantsmanagement.GrantsManagement.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query("SELECT c FROM Email c WHERE c.foundationId = ?1 AND (COALESCE(?2, NULL) IS NULL OR c.nonProfitEmail IN (?2))")
    List<Email> findAllEmailsByFoundationIdNonProfitIds(Long foundationId, List<String> nonProfitEmails);

}
