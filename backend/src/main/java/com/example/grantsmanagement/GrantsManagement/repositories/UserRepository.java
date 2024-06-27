package com.example.grantsmanagement.GrantsManagement.repositories;

import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select c from User c where c.username = ?1")
    User findUserByUsername(String username);
}
