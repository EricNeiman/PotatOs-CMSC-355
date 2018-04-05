package com.example.server.REST;

import com.example.common.Quiz;
import com.example.common.REST.UserREST;
import com.example.common.User;
import com.example.server.DatabaseHelper.QuizTable;
import com.example.server.DatabaseHelper.UserTable;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class QuizResource {
    @POST
    @Path(UserREST.GET_QUIZ_BY_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuizById(int id) {
        Gson gson = new Gson();
        Response rs;
        try {
            Quiz quiz = QuizTable.getQuizById(id);

            rs = Response.status(Response.Status.OK).entity(
                    gson.toJson(quiz)
            ).build();
        } catch (JsonSyntaxException ex) {
            System.out.println("Invalid json received.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            System.out.println("SQL Error.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }

    @POST
    @Path(UserREST.CREATE_QUIZ)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuiz(String message) {
        System.out.println("Creating a user..." + message);
        Gson gson = new Gson();
        Response rs;

        try {
            Quiz quiz = gson.fromJson(message, Quiz.class);
            QuizTable.createQuiz(quiz);
            rs = Response.status(Response.Status.OK).entity(gson.toJson(quiz)).build();
        } catch (JsonSyntaxException ex) {
            System.out.println("Invalid json received.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            System.out.println("SQL Error.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }
}
