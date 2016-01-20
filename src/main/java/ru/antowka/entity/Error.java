package ru.antowka.entity;

/**
 * Created by Anton Nik on 14.01.16.
 */
public class Error {

    private String header;
    private String title;
    private String Description;

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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
