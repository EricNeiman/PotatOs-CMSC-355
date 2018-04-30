package com.example.server.REST;

import com.example.common.Answer;
import com.example.common.Question;
import com.example.common.Quiz;
import com.example.common.REST.PotatOsApi;
import com.example.common.REST.QuizREST;
import com.example.common.REST.UserREST;
import com.example.common.User;
import com.example.server.DatabaseHelper.AnswerTable;
import com.example.server.DatabaseHelper.QuestionTable;
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
@Path(PotatOsApi.API_DIR)
public class QuizResource {
    @POST
    @Path(QuizREST.GET_QUIZ_BY_ID)
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
            ex.printStackTrace();
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL Error.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }

    @POST
    @Path(QuizREST.CREATE_QUIZ)
    //this will create the quiz passed, creating all the contained answers and questions
    public Response createQuiz(String message) {
        System.out.println("Creating a quiz..." + message);
        Gson gson = new Gson();
        Response rs;

        try {
            Quiz quiz = gson.fromJson(message, Quiz.class);
            QuizTable.createQuiz(quiz);

            //we also need to create the questions, answers if they haven't been created
            //we consider them already created if they have an id set
            for (Question q: quiz.getQuestions()) {
                if (q.getId()!=0){
                    QuestionTable.createQuestion(q);

                    for (Answer a: q.getAnswers()) {
                        if (a.getId()!=0) {
                            AnswerTable.createAnswer(a);
                        }
                    }
                }
            }

            rs = Response.status(Response.Status.OK).entity(gson.toJson(quiz)).build();
        } catch (JsonSyntaxException ex) {
            System.out.println("Invalid json received.");
            ex.printStackTrace();
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            System.out.println("SQL Error.");
            ex.printStackTrace();
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }
}
