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
    public static final String DELETE_USER = "deleteUser";
    public static final String GET_USER_BY_ID = "createUserById";

    //this one must have form data fields "email" and "passwordHash" set to the corresponding strings in the request
    public static final String GET_USER_BY_EMAIL_PASS = "getUserByEmailPass";


    public static User getByEmailPass(String email, String passwordHash) {
        Gson gson = new Gson();
        ClassUserPassRequest form = new ClassUserPassRequest();
        form.email = email;
        form.passwordHash = passwordHash;

        String requestJson = gson.toJson(form);
        String json = PotatOsApi.postJson(GET_USER_BY_EMAIL_PASS, requestJson, true);
        return gson.fromJson(json, User.class);
    }

    //returns the copy of the user as interpreted by the server
    //Returns null if the user couldn't be created
    //ID is ignored from the input, and the returned user will be assigned an ID by the server.
    public static User createUser(User user) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(user);
        String resp = PotatOsApi.postJson(CREATE_USER, requestJson, true);

        if (resp != null) {
            int newID = Integer.parseInt(resp);
            user.setId(newID);
            return user;
        } else {
            return null;
        }
    }

    public static User getById(int userID) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(GET_USER_BY_ID + "/" + userID, true);
        return gson.fromJson(json, User.class);
    }


    //deletes the user from the database
    public boolean delete(User user) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(user.getId());
        String resp = PotatOsApi.postJson(DELETE_USER, requestJson, true);
        return true;
    }


    public static boolean updateUser(User user) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(new SmallUser(user));
        String resp = PotatOsApi.postJson(UPDATE_USER, requestJson, true);

        if (resp != null) {
            return true;
        } else {
            return false;
        }
    }
}
