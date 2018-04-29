package com.example.common.REST;

import com.example.common.Quiz;
import com.google.gson.Gson;

/**
 * Created by vita on 4/3/18.
 */

public class QuizREST {

    public static final String CREATE_QUIZ = "createQuiz";
    public static final String GET_QUIZ_BY_ID = "getQuizById";

    public static Quiz createQuiz(Quiz quiz) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(quiz);
        String resp = PotatOsApi.postJson(CREATE_QUIZ, requestJson);

        if (resp == null) {
            int newID = Integer.getInteger(resp);
            quiz.setId(newID);
            return quiz;
        } else {
            return null;
        }
    }

    public static Quiz getQuizById(int quizID) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(quizID);
        String resp = PotatOsApi.postJson(GET_QUIZ_BY_ID, requestJson);

        if (resp == null) {
            return gson.fromJson(resp, Quiz.class);
        } else {
            return null;
        }

    }
}
