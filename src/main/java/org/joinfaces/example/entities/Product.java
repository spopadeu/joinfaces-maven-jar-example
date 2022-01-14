package org.joinfaces.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Getter
@Setter
@Entity


public class Product implements Serializable {

    @Id
    @SequenceGenerator(name = "pSGen", sequenceName = "productSeq",
            initialValue = 50)
    @GeneratedValue(generator = "pSGen")
    private int id;
    private String code;
    private String name;
    private String description;
    private String image;
    private double price;
    private String category;
    private int quantity;
    private int rating;

    public Product(int id, String code, String name, String description, String image, double price, String category, int quantity, int rating) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.rating = rating;
    }

    public Product() {

    }
}