package com.example.common;

public class Question {
    private String prompt; // question text
    private QuestionImage image; // picture for question
    private Answer[] answers; // 4 answer objects attached
    private int pointValue; // points possible for question
    private int id; //database id

    public Question(String prompt, QuestionImage image, Boolean correct, Answer[] answers, int pointValue, int id) {
        this.prompt = prompt;
        this.image =  image;
        this.answers = answers;
        this.pointValue = pointValue;
        this.id = id;
    } // basic constructor

    public Question() {
        this.prompt = "";
        this.image =  null;
        this.answers = null;
        this.pointValue = 0;
        this.id = 0;
    } // default constructor

    // getters and setters for variables
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setImage(QuestionImage image) {
        this.image = image;
    }
    public QuestionImage getImage() {
        return image;
    }

    public Boolean getCorrect() {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].getSelected() && answers[i].getCorrect()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnswered() {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].getSelected()) {
                return true;
            }
        }
        return false;
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

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}