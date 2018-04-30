package com.example.common.REST;

import com.google.gson.Gson;

import static com.example.common.REST.PotatOsApi.postJson;

public class QuestionREST {
    public static final String DELETE_QUESTION = "deleteQuestion";

    public static void deleteQuestion(int id) {
        Gson gson = new Gson();
        postJson(DELETE_QUESTION, gson.toJson(id), true);
    }
}
