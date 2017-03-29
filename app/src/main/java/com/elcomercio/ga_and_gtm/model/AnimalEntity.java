package com.elcomercio.ga_and_gtm.model;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 29/03/17.
 */

public class AnimalEntity {

    private boolean favorite;
    private String title;

    public AnimalEntity(boolean favorite, String title) {
        this.favorite = favorite;
        this.title = title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
