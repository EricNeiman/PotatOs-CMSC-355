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
    User user;
    String[] quizzes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_quizzes);

        Gson gson = new Gson();
        json = getIntent().getStringExtra("com.example.potatoes.logIn");
        user = gson.fromJson(json , User.class);

        quizListView = findViewById(R.id.quizNameListView);
        ArrayList<Class> classList = user.getClassesIn();
        int i = 0;
        for (Class object: classList) {
            for (Quiz quiz: object.getQuizzes(){
                quizzes[i] = quiz.getQuizName();
                i++;
            }
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
                showQuizQuestions.putExtras(extras);
                startActivity(showQuizQuestions);
            }
        });

        Button createQuizBtn = findViewById(R.id.createQuizbtn);
        createQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, QuizInfo.class);
                startActivity(i);
            }
        });

        Button logOutBtn = findViewById(R.id.logOutbtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
