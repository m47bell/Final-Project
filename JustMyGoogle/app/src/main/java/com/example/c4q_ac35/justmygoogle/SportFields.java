package com.example.c4q_ac35.justmygoogle;

/**
 * Created by c4q-marbella on 6/23/15.
 */
public class SportFields {

    private String title;
    private String category;
    private String description;


    public SportFields(String category, String description, String title) {
        this.category = category;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
