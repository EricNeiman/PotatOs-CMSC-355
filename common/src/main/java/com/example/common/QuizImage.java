package com.example.common;

public class QuizImage {
    private String imagePath; //should be the image path under the quiz folder
    private byte[] imageData;

    public String getImagePath() {
        return imagePath;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
