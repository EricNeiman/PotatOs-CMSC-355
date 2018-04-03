package com.example.common;

import javax.ws.rs.Path;

public class Answer {
    private String letter; // answer letter (A, B, C, or D)
    private String answerText; // answer text (Limit of 60 character)
    private String feedback; // explanation for why the answer ir correct/incorrect
    private boolean correct; // true if answer was correct
    private boolean selected; // true if answer was selected

    public Answer(String letter, String answerText, String feedback, boolean correct, boolean selected) {
        this.letter = letter;
        this.answerText = answerText;
        this.feedback = feedback;
        this.correct = correct;
        this.selected = selected;
    } // basic constructor

    public Answer() {
        this.letter = "";
        this.answerText = "";
        this.feedback = "";
        this.correct = false;
        this.selected = false;
    } // default constructor

    // getters and setters for each variable
    public String getAnswerText() {return answerText;}
    public void setAnswerText(String answerText) {this.answerText = answerText;}

    public String getFeedback() {return feedback;}
    public void setFeedback(String feedback) {this.feedback = feedback;}

    public String getLetter() {return letter;}
    public void setLetter(String letter) {this.letter = letter;}

    public boolean getCorrect() {return correct;}
    public void setCorrect(boolean correct) {this.correct = correct;}

    public boolean getSelected() {return selected;}
    public void setSelected(boolean selected) {this.selected = selected;}
}
