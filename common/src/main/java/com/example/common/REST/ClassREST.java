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

    public static void createClass(Class input) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(CREATE_CLASS, gson.toJson(new SmallClass(input)), true);

        SmallClass newCls = gson.fromJson(json, SmallClass.class);
        input.setClassID(newCls.getClassID());
    }

    public static Class getClassById(int id) {
        Gson gson = new Gson();
        String json = PotatOsApi.postJson(GET_CLASS_BY_ID, gson.toJson(id), true);
        SmallClass cls = gson.fromJson(json, SmallClass.class);
        return new Class(cls);
    }
}
