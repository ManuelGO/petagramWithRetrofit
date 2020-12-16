package com.example.petagram.pojo;

public class Pet {
    //TODO: add img url property
    private String name;
    private String rating;
    private int image;
    private String media_url;
    private int id;

    public Pet(String name, String rating, int image, String media_url) {
        this.name = name;
        this.rating = rating;
        this.image = image;
        this.media_url = media_url;
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

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }
}
