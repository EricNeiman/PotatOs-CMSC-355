package com.example.common;

public class QuestionImage {
    private String fileName; // file name (includes extension)
    private String imagePath; // should be the image path under the quiz folder TODO find android image API
    private byte[] imageData; // for transferring to server
    private int id;

    public QuestionImage(String fileName, String imagePath, byte[] imageData, int id) {
        this.fileName = fileName;
        this.imagePath = imagePath;
        this.imageData = imageData;
        this.id = id;
    } // basic constructor

    public QuestionImage() {
        this.fileName = "";
        this.imagePath = "";
        this.imageData = null;
        this.id = 0;
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

    public int getId() { return id; }
    public void setId(int id) {this.id = id;}

}