package com.example.server.REST;

import com.example.common.REST.PotatOsApi;
import com.example.common.REST.QuestionREST;
import com.example.common.REST.QuizREST;
import com.example.server.DatabaseHelper.QuestionTable;

import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path(PotatOsApi.API_DIR)
public class QuestionResource {

    @POST
    @Path(QuestionREST.DELETE_QUESTION)
    public static Response deleteQuestion(String message) {
        try {
            QuestionTable.deleteQuestion(Integer.parseInt(message));
            return Response.status(Response.Status.OK).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
