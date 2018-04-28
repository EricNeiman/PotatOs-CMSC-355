package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.common.Class;
import com.example.common.Quiz;
import com.example.common.User;
import com.google.gson.Gson;

import java.util.ArrayList;


public class ClassQuizzes extends Activity {

    ListView quizListView;
    String json;
    String jsonClass;
    String jsonQuiz;
    User user;
    Class userClass;
    String[] quizzes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_quizzes);

        quizListView = findViewById(R.id.quizNameListView);

        Gson gson = new Gson();
        json = getIntent().getStringExtra("com.example.potatoes.logIn");
        user = gson.fromJson(json , User.class);
        jsonClass = getIntent().getStringExtra("com.example.potatoes.CLASS");
        userClass = gson.fromJson(jsonClass, Class.class);

        final ArrayList<Quiz> quizList = userClass.getQuizzes();
        int i = 0;
        for (Quiz object: quizList){
            quizzes[i] = object.getQuizName();
            i++;
        }


        AdapterQuiz quizAdapter = new AdapterQuiz(this, quizzes);
        quizListView.setAdapter(quizAdapter);

        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showQuizQuestions = new Intent(getApplicationContext(), QuizQuestions.class);
                Bundle extras = new Bundle();
                extras.putInt("com.example.potatos.QUIZ_INDEX", position);
                extras.putString("com.example.potatoes.logIn", json);
                extras.putString("com.example.potatoes.CLASS", jsonClass);
                Gson gson = new Gson();
                jsonQuiz = gson.toJson(quizList.get(position));
                extras.putString("com.example.potatoes.QUIZ", jsonQuiz);
                showQuizQuestions.putExtras(extras);
                startActivity(showQuizQuestions);
            }
        });

        Button createQuizBtn = findViewById(R.id.createQuizbtn);
        createQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QuizInfo.class);
                startActivity(i);
            }
        });

        Button logOutBtn = findViewById(R.id.logOutbtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
