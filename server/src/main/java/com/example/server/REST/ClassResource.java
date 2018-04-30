package com.example.server.REST;

import com.example.common.REST.ClassREST;
import com.example.common.REST.PotatOsApi;
import com.example.common.REST.SmallClass;
import com.example.server.DatabaseHelper.ClassTable;
import com.example.server.DatabaseHelper.EnrollmentsTable;
import com.example.server.DatabaseHelper.QuizTable;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

@Path(PotatOsApi.API_DIR)
public class ClassResource {
    @POST
    @Path(ClassREST.CREATE_CLASS)
    public Response createClass(String message) {
        Response rs;
        try {
            Gson gson = new Gson();
            SmallClass cls = gson.fromJson(message, SmallClass.class);

            ClassTable.createClass(cls);
            System.out.println("Created a class, id: " + cls.getClassID() + ", name: " + cls.getClassName());
            rs = Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(cls)
                    )
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }

    @POST
    @Path(ClassREST.GET_CLASSES_FOR_USER_ID)
    public Response getClassesForUserId(String message) {
        Gson gson = new Gson();

        int id = gson.fromJson(message, int.class);
        try {
            return Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(ClassTable.getClassesForUserId(id))
                    )
                    .build();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @POST
    @Path(ClassREST.GET_CLASS_BY_ID)
    public Response getClassById(String message) {
        Gson gson = new Gson();

        int id = gson.fromJson(message, int.class);

        try {
            SmallClass cls = ClassTable.getClassById(id);
            //    private ArrayList<Integer> enrolledUsers;
            //    private ArrayList<Integer> quizzes;

            cls.setEnrolledUsers(EnrollmentsTable.getEnrolledUsers(cls.getClassID()));
            cls.setQuizzes(QuizTable.getQuizzesByClassId(cls.getClassID()));

            return Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(cls)
                    )
                    .build();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
