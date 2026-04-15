package com.vsrdev.terminux_review_api.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String bookTitle;
    @Column(nullable = false)
    private String reviewText;
    @Column(nullable = false)
    private Integer rating;
    

    // construtores
    public ReviewEntity() {
    }

    public ReviewEntity(Long id, String userName, String bookTitle, String reviewText, Integer rating) {
        this.id = id;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Integer getRating() {
        return rating;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}