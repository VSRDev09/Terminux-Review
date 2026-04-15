package com.vsrdev.terminux_review_api.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.vsrdev.terminux_review_api.model.entity.ReviewEntity;

public interface JpaReviewRepository extends JpaRepository<ReviewEntity, Long> {

    boolean existsByUserNameAndBookTitle(String userName, String bookTitle);

    List<ReviewEntity> findByBookTitleContainingIgnoreCase(String bookTitle);
}
