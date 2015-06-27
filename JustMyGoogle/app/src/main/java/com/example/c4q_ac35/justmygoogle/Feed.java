package com.example.c4q_ac35.justmygoogle;

/**
 * Created by c4q-marbella on 6/26/15.
 */
public class Feed {
    private String title;
    private String category;
    private String link;
    private String description;
    private String pubDate;
    private String urlImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String artist) {
        this.link = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String releaseDate) {
        this.description = releaseDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCategory() {
        return category;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
