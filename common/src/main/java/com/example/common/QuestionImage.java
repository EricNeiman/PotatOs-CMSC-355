package com.example.common;

public class QuestionImage {
    private String fileName; // file name (includes extension)
    private String imagePath; // should be the image path under the quiz folder TODO find android image API
    private byte[] imageData; // for transferring to server

    public QuestionImage(String fileName, String imagePath, byte[] imageData) {
        this.fileName = fileName;
        this.imagePath = imagePath;
        this.imageData = imageData;
    } // basic constructor

    public QuestionImage() {
        this.fileName = "";
        this.imagePath = "";
        this.imageData = null;
    } // default constructor

    // getters and setters for variables
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public byte[] getImageData() {
        return imageData;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}