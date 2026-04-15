package com.vsrdev.terminux_review_api.model.entity;

public class Book {
    
    private String title;

    public Book(String title){

        if (title == null || title.isBlank()){
             throw new IllegalArgumentException("Titulo invalido");
        }

        this.title = title;
    }

    public String getTitle(){

        return this.title;
        
    }
}
