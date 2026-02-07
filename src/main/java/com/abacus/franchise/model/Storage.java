package com.abacus.franchise.model;

import java.io.InputStream;

public class Storage {
    private String path;
    private String fileName;
    private InputStream inputStream;

    public Storage() {
    }

    public Storage(String path, String fileName, InputStream inputStream) {
        this.path = path;
        this.fileName = fileName;
        this.inputStream = inputStream;
    }

    // Getters and setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
