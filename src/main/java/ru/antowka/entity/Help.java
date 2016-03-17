package ru.antowka.entity;

/**
 * Created by Anton Nik on 20.01.16.
 */
public abstract class Help {

    protected String title;
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void show(){

    }
}
