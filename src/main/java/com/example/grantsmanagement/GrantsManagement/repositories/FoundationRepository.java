package com.example.grantsmanagement.GrantsManagement.repositories;

import com.example.grantsmanagement.GrantsManagement.models.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoundationRepository extends JpaRepository<Foundation, Long> {
}
