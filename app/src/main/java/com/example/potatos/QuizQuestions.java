package com.example.potatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.common.Quiz;
import com.example.common.User;
import com.example.common.Class;

import java.util.ArrayList;

public class QuizQuestions extends AppCompatActivity {
    Toolbar toolbar;
    ListView questListView;
    String json;
    String jsonClass;
    String jsonQuiz;
    String jsonQuestion;
    User user;
    Class userClass;
    Quiz userQuiz;
    ArrayList<com.example.common.Question> questionList;
    int[] numberOrder;
    int[] pointValues;
    String[] questionAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        questListView = findViewById(R.id.questionListView);

        json = getIntent().getStringExtra("com.example.potatos.logIn");
        jsonClass = getIntent().getStringExtra("com.example.potatos.CLASS");
        jsonQuiz = getIntent().getStringExtra("com.example.potatos.QUIZ");
        user = User.fromJson(json);
        userClass = Class.fromJson(jsonClass);
        userQuiz = Quiz.fromJson(jsonQuiz);

        questionList = userQuiz.getQuestions();
        numberOrder = new int[questionList.size()];
        pointValues = new int[questionList.size()];
        questionAnswered = new String[questionList.size()];
        int i = 0;
        for (com.example.common.Question object: questionList){
            numberOrder[i] = i+1;
            i++;
        }
        i = 0;
        for (com.example.common.Question object: questionList){
            pointValues[i] = object.getPointValue();
            i++;
        }
        i = 0;
        for (com.example.common.Question object: questionList) {
            if (object.isAnswered())
                questionAnswered[i] = "Answered";
            else
                questionAnswered[i] = "Not Answered";
            i++;
        }

        AdapterQuestion questionAdapter = new AdapterQuestion(this, numberOrder, pointValues, questionAnswered);
        questListView.setAdapter(questionAdapter);

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showQuestions = new Intent(getApplicationContext(), Question.class);
                Bundle extras = new Bundle();
                extras.putInt("com.example.potatos.QUESTION_INDEX", position);
                extras.putString("com.example.potatos.logIn", json);
                extras.putString("com.example.potatos.CLASS", jsonClass);
                extras.putString("com.example.potatos.QUIZ", jsonQuiz);
                jsonQuestion = questionList.get(position).toJson();
                extras.putString("com.example.potatos.QUESTION", jsonQuestion);

                showQuestions.putExtras(extras);
                startActivity(showQuestions);
            }
        });
    }
}
