package com.example.DDD_test.repository;

import com.example.DDD_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
