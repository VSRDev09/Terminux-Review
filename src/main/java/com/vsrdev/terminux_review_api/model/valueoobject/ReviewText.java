package com.vsrdev.terminux_review_api.model.valueoobject;

public class ReviewText {
    
    private final String reviewText;

    public ReviewText(String reviewText){
        if(reviewText == null || reviewText.isBlank()){
            throw new IllegalArgumentException("Conteudo da review nao pode estar vazio");
        }

        if (reviewText.length() < 10) {
        throw new IllegalArgumentException("Texto muito curto");
    }
        this.reviewText = reviewText;
    }

    public String getReviewText(){

        return reviewText;

    }

    
}
