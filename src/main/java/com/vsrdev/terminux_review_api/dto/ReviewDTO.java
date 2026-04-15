package com.vsrdev.terminux_review_api.dto;

import jakarta.validation.constraints.*;


public class ReviewDTO {

    private Long id;
    
    @NotBlank(message = "Nome não pode ser vazio")
    private String userName;

    @NotBlank(message = "Livro precisa ter título")
    private String bookTitle;

    @NotNull(message = "Avaliação precisa ser informada")
    @Min(value = 0, message = "Avaliação não pode ser negativa")
    @Max(value = 10, message = "Nota máxima da avaliaçao é 10")
    private Integer rating;


    @Size(min = 10, message = "Review deve ter pelo menos 10 caracteres")
    private String reviewText;

    public ReviewDTO(){

    }

    public Long getId(){
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public String getBookTitle(){
        return bookTitle;
    }

    public int getRating(){
        return rating;
    }

    public String getReviewText(){
        return reviewText;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public void setReviewText(String reviewText){
        this.reviewText = reviewText;
    }

}

