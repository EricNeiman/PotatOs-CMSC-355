package com.example.common.REST;

import com.example.common.Quiz;
import com.example.common.User;
import com.google.gson.Gson;

/**
 * Created by vita on 4/3/18.
 */

public class QuizREST {

    public static final String CREATE_QUIZ = "createQuiz";
    public static final String GET_QUIZ_BY_ID = "getQuizById";

    public Quiz createQuiz(Quiz quiz) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(quiz);
        String resp = PotatOsApi.postJson(CREATE_QUIZ, requestJson);

        if (resp == null) {
            int newID = Integer.getInteger(resp);
            quiz.setQuizId(newID);
            return quiz;
        } else {
            return null;
        }
    }

    public Quiz getById(int quizID) {
        return null;
    }
}
