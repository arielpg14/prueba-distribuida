package com.distribuida.db;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Data
public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private Double price;



    public Book (){

     }
    public Book (Integer id, String isbn, String title, String author, Double price){

        this.id=id;
        this.isbn=isbn;
        this.title=title;
        this.author=author;
        this.price=price;
    }
}

