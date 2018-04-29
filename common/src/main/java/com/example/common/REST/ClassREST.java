package com.example.common.REST;

import com.example.common.User;
import com.google.gson.Gson;

import com.example.common.Class;

/**
 * Created by vita on 4/3/18.
 */

public class ClassREST {
    public static final String GET_CLASSES_FOR_USER_ID = "getClassesForUserId";
    public static final String GET_CLASS_BY_ID = "getClassById";
    public static final String CREATE_CLASS = "getClass";

    public static Class createClass(Class input) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(CREATE_CLASS, gson.toJson(new SmallClass(input)));
        return new Class(gson.fromJson(json, SmallClass.class));
    }

    public static Class getClassById(int id) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(GET_CLASS_BY_ID, gson.toJson(id));

        return new Class(gson.fromJson(json, SmallClass.class));
    }
}
