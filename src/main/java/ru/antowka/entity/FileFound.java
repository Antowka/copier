package ru.antowka.entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

import java.nio.file.Path;

/**
 * Created by Anton Nik on 15.01.16.
 */
public class FileFound {

    private String fileName;
    private Long fileSize;
    private String fileExt;
    private String fullPath;
    private Path filePath;
    private BooleanProperty checked = new SimpleBooleanProperty(true);


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public ObservableBooleanValue isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }
}
