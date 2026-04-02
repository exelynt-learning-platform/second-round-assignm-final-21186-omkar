package com.example.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.*;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
