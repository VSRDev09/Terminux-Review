package com.vsrdev.terminux_review_api.mapper;

import com.vsrdev.terminux_review_api.dto.ReviewDTO;
import com.vsrdev.terminux_review_api.model.domain.Review;
import com.vsrdev.terminux_review_api.model.entity.ReviewEntity;
import com.vsrdev.terminux_review_api.model.valueoobject.BookTitle;
import com.vsrdev.terminux_review_api.model.valueoobject.Rating;
import com.vsrdev.terminux_review_api.model.valueoobject.ReviewText;
import com.vsrdev.terminux_review_api.model.valueoobject.UserName;

public class ReviewMapper {

    public static ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();

        dto.setId(review.getId());
        dto.setUserName(review.getUserName());
        dto.setBookTitle(review.getBookTitle());
        dto.setReviewText(review.getReviewText());
        dto.setRating(review.getRating());

        return dto;
    }

    public static Review toDomain(ReviewDTO dto) {

        UserName user = new UserName(dto.getUserName());
        BookTitle book = new BookTitle(dto.getBookTitle());
        Rating rating = new Rating(dto.getRating());
        ReviewText content = new ReviewText(dto.getReviewText());

        if (dto.getId() != null) {
            return new Review(dto.getId(), user, book, rating, content);
        }

        return new Review(user, book, rating, content);
    }

    public static ReviewEntity toEntity(Review review) {
        return new ReviewEntity(
                review.getId(),
                review.getUserName(),
                review.getBookTitle(),
                review.getReviewText(),
                review.getRating()
        );
    }

    public static Review toDomain(ReviewEntity entity) {


        return new Review(
                entity.getId(),
                new UserName(entity.getUserName()),
                new BookTitle(entity.getBookTitle()),
                new Rating(entity.getRating()),
                new ReviewText(entity.getReviewText())
        );
    }
}