package com.vsrdev.terminux_review_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.vsrdev.terminux_review_api.mapper.ReviewMapper;
import com.vsrdev.terminux_review_api.model.domain.Review;
import com.vsrdev.terminux_review_api.model.entity.ReviewEntity;

@Repository
@Primary
public class JpaReviewRepositoryAdapter implements ReviewRepository {

    private final JpaReviewRepository jpaRepository;

    public JpaReviewRepositoryAdapter(JpaReviewRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Review save(Review review) {
        ReviewEntity entity = ReviewMapper.toEntity(review);

        ReviewEntity saved = jpaRepository.save(entity);

        return ReviewMapper.toDomain(saved); // 🔥 agora vem com ID
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ReviewMapper::toDomain);
    }

    @Override
    public List<Review> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserNameAndBookTitle(String userName, String bookTitle) {
        return jpaRepository.existsByUserNameAndBookTitle(userName, bookTitle);
    }

    @Override
    public List<Review> findByBookTitle(String book) {
        return jpaRepository.findByBookTitleContainingIgnoreCase(book)
                .stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }
}