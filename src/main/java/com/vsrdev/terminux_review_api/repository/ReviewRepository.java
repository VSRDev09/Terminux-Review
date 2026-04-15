package com.vsrdev.terminux_review_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vsrdev.terminux_review_api.model.domain.Review;

@Repository
public interface ReviewRepository{

    Review save(Review review);

    List<Review> findAll();

    List<Review> findByBookTitle(String book);

    Optional<Review> findById(Long id);

    void deleteById(Long id);

    boolean existsByUserNameAndBookTitle(String userName, String bookTitle);
}