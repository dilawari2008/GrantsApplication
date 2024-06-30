package com.example.grantsmanagement.GrantsManagement.repositories;

import com.example.grantsmanagement.GrantsManagement.models.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query("select e, np.name from Email e left join NonProfit np on e.nonProfitId = np.id where e.foundationId = ?1")
    Page<Email> findAllEmailsByFoundationIdNonProfitIds(Long foundationId, Pageable pageable);

}
