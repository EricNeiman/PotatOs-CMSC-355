package com.example.common;

import javax.ws.rs.Path;

public class Answer {
    String letter;
    String answerText;
    String feedback;
    boolean correct;
    boolean selected;

    public String getAnswerText() {
        return answerText;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getLetter() {
        return letter;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
