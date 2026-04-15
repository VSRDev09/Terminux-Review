package com.vsrdev.terminux_review_api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vsrdev.terminux_review_api.dto.ReviewDTO;
import com.vsrdev.terminux_review_api.dto.ReviewPatchDTO;
import com.vsrdev.terminux_review_api.exception.BusinessException;
import com.vsrdev.terminux_review_api.exception.ResourceNotFoundException;
import com.vsrdev.terminux_review_api.mapper.ReviewMapper;
import com.vsrdev.terminux_review_api.model.domain.Review;
import com.vsrdev.terminux_review_api.model.valueoobject.BookTitle;
import com.vsrdev.terminux_review_api.model.valueoobject.Rating;
import com.vsrdev.terminux_review_api.model.valueoobject.ReviewText;
import com.vsrdev.terminux_review_api.model.valueoobject.UserName;
import com.vsrdev.terminux_review_api.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<ReviewDTO> listReviews() {
        log.info("Listando todas as reviews: ");

        return repository.findAll()
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    public ReviewDTO createReview(ReviewDTO dto) {

        if (dto.getId() != null) {
            throw new BusinessException("ID não deve ser informado na criação");
        }

        if (repository.existsByUserNameAndBookTitle(
                dto.getUserName(),
                dto.getBookTitle())) {
            throw new BusinessException("Review already exists");
        }


        Review review = ReviewMapper.toDomain(dto);

        Review saved = repository.save(review); // 🔥 pega retorno

        return ReviewMapper.toDTO(saved); // 🔥 usa saved
    }

    public ReviewDTO findById(Long id) {

        Review review = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review não encontrada"));

        return ReviewMapper.toDTO(review);
    }

    public void delete(Long id) {

        if (!repository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Review não encontrada");
        }

        repository.deleteById(id);
    }

    public List<ReviewDTO> findByBookTitle(String book) {

        if (book == null || book.isBlank()) {
            throw new IllegalArgumentException("Book não pode ser vazio");
        }

        log.info("Buscando reviews para livro: {}", book);

        return repository.findByBookTitle(book).stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    public ReviewDTO update(Long id, ReviewDTO dto) {

        if (dto.getId() != null && !dto.getId().equals(id)) {
            throw new IllegalArgumentException("ID não pode ser alterado");
        }

        Review review = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review não encontrada"));

        Review updated = review.update(
                dto.getBookTitle(),
                dto.getRating(),
                dto.getReviewText());

        Review saved = repository.save(updated); // 🔥 importante

        return ReviewMapper.toDTO(saved);
    }

    public ReviewDTO patchReview(Long id, ReviewPatchDTO dto) {

        if (dto.getBookTitle() == null &&
                dto.getRating() == null &&
                dto.getReviewText() == null) {
            throw new BusinessException("Pelo menos um campo deve ser fornecido");
        }


        Review existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review não encontrada"));

        String bookTitle = dto.getBookTitle() != null
                ? dto.getBookTitle()
                : existing.getBookTitle();

        int rating = dto.getRating() != null
                ? dto.getRating()
                : existing.getRating();

        String reviewText = dto.getReviewText() != null
                ? dto.getReviewText()
                : existing.getReviewText();

        Review updated = new Review(
                existing.getId(),
                new UserName(existing.getUserName()),
                new BookTitle(bookTitle),
                new Rating(rating),
                new ReviewText(reviewText));

        Review saved = repository.save(updated); // 🔥

        return ReviewMapper.toDTO(saved);
    }

}
