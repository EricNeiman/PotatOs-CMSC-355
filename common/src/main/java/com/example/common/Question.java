package com.example.common;

public class Question {
    private String prompt; // question text
    private QuizImage image; // picture for question
    private Boolean correct; // true if user selected correct answer
    private Answer[] answers; // 4 answer objects attached
    private int pointValue; // points possible for question

    public Question(String prompt, QuizImage image, Boolean correct, Answer[] answers, int pointValue) {
        this.prompt = prompt;
        this.image =  image;
        this.correct = correct;
        this.answers = answers;
        this.pointValue = pointValue;
    } // basic constructor

    public Question() {
        this.prompt = "";
        this.image =  null;
        this.correct = false;
        this.answers = null;
        this.pointValue = 0;
    } // default constructor

    // getters and setters for variables
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setImage(QuizImage image) {
        this.image = image;
    }
    public QuizImage getImage() {
        return image;
    }

    public Boolean getCorrect() {
        return correct;
    }
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Answer[] getAnswers() {
        return answers;
    }
    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
    public int getPointValue() {
        return pointValue;
    }
}