package com.example.common.REST;

import com.example.common.User;
import com.google.gson.Gson;

/**
 * Created by vita on 4/3/18.
 */

public class UserREST {
    //provided api functions
    public static final String CREATE_USER = "createUser";
    public static final String UPDATE_USER = "updateUser";
    public static final String GET_USER_BY_ID = "createUserById";

    //this one must have form data fields "email" and "passwordHash" set to the corresponding strings in the request
    public static final String GET_USER_BY_EMAIL_PASS = "getUserByEmailPass";
    public static final String CREATE_QUIZ = "createQuiz";
    public static final String GET_QUIZ_BY_ID = "getQuizById";

    public static User getByEmailPass(String email, String passwordHash) {
        Gson gson = new Gson();
        ClassUserPassRequest form = new ClassUserPassRequest();
        form.email = email;
        form.passwordHash = passwordHash;

        String requestJson = gson.toJson(form);
        String json = PotatOsApi.postJson(GET_USER_BY_EMAIL_PASS, requestJson);
        return gson.fromJson(json, User.class);
    }

    //returns the copy of the user as interpreted by the server
    //ID is ignored from the input
    public static User createUser(User input) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(input);
        String json = PotatOsApi.postJson(CREATE_USER, requestJson);
        return gson.fromJson(json, User.class);
    }

    public static User getById(int userID) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(GET_USER_BY_ID + "/" + userID);
        return gson.fromJson(json, User.class);
    }


    public boolean delete(User input) {
        return false;
    }


}
