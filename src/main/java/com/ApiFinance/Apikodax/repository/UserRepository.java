package com.ApiFinance.Apikodax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiFinance.Apikodax.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
