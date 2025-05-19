package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{

}

