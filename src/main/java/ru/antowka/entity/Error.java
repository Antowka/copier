package ru.antowka.entity;

/**
 * Created by Anton Nik on 14.01.16.
 */
public abstract class Error {

    protected String header;
    protected String title;
    protected String description;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public void show(){

    }
}
