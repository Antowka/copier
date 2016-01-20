package ru.antowka.entity;

/**
 * Created by Anton Nik on 20.01.16.
 */
public abstract class Help {

    private String title;
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
