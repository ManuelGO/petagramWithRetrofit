package com.example.petagram.pojo;

public class Pet {
    private String name;
    private String rating;
    private int image;
    private  int id;

    public Pet(String name, String rating, int image) {
        this.name = name;
        this.rating = rating;
        this.image = image;
    }

    public Pet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
