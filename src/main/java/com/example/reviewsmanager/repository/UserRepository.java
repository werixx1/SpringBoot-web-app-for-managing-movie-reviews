package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);

    // STATISTICS
    // user with most reviews
    @Query("""
    SELECT u
    FROM User u
    JOIN u.reviewsPosted r
    GROUP BY u
    ORDER BY COUNT(r) DESC
    """)
    User findUserWithMostReviews();

    // inactive users (no reviews) <- i know it doesnt really work like that,
    // its just for presentation purposes
    @Query("""
    SELECT u
    FROM User u
    LEFT JOIN u.reviewsPosted r
    GROUP BY u
    HAVING COUNT(r) = 0
    """)
    List<User> findInactiveUsers();
}

