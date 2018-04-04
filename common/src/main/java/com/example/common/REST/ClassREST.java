package com.example.common.REST;

import com.google.gson.Gson;

import com.example.common.Class;

/**
 * Created by vita on 4/3/18.
 */

public class ClassREST {
    public static final String GET_CLASSES_FOR_USER_ID = "getClassesForUserId";
    public static final String CREATE_CLASS = "getClass";

    public Class create(Class input) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(CREATE_CLASS, gson.toJson(input));
        return gson.fromJson(json, Class.class);
    }
}
