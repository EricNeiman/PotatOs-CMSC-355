package com.example.common;

public class Question {
    private String prompt;
    private QuizImage image;
    private Boolean correct;
    private Answer[] answers;
    private int pointValue;

    public Answer[] getAnswers() {
        return answers;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setImage(QuizImage image) {
        this.image = image;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public QuizImage getImagePath() {
        return image;
    }

    public int getPointValue() {
        return pointValue;
    }

    public String getPrompt() {
        return prompt;
    }




}
