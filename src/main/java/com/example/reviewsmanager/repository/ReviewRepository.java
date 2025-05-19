package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>
{

}
