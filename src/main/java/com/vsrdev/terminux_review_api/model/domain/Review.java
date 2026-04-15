package com.vsrdev.terminux_review_api.model.domain;

import com.vsrdev.terminux_review_api.model.valueoobject.BookTitle;
import com.vsrdev.terminux_review_api.model.valueoobject.Rating;
import com.vsrdev.terminux_review_api.model.valueoobject.ReviewText;
import com.vsrdev.terminux_review_api.model.valueoobject.UserName;

public class Review {

    private Long id;
    private final UserName userName;
    private final BookTitle bookTitle;
    private final Rating rating;
    private final ReviewText reviewText;

    public Review(UserName userName, BookTitle bookTitle, Rating rating, ReviewText reviewText) {
        if (userName == null || bookTitle == null || rating == null || reviewText == null) {
            throw new IllegalArgumentException("Review invalida");
        }


        this.id = null;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Review(Long id, UserName userName, BookTitle bookTitle, Rating rating, ReviewText reviewText) {
        if (userName == null || bookTitle == null || rating == null || reviewText == null) {
            throw new IllegalArgumentException("Review invalida");
        }

        this.id = id;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName.getValue();
    }

    public String getBookTitle() {
        return bookTitle.getValue();
    }

    public String getReviewText() {
        return reviewText.getReviewText();
    }

    public int getRating() {
        return rating.getValue();
    }

    public Review update(String bookTitle, int rating, String text) {
    return new Review(
        this.id,
        this.userName,
        new BookTitle(bookTitle),
        new Rating(rating),
        new ReviewText(text)
    );
}

}
