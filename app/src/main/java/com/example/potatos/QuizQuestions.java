package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.common.Quiz;
import com.example.common.User;
import com.example.common.Question;
import com.google.gson.Gson;

import java.util.ArrayList;

public class QuizQuestions extends Activity {

    ListView questListView;
    String json;
    String jsonClass;
    String jsonQuiz;
    String jsonQuestion;
    User user;
    Class userClass;
    Quiz userQuiz;
    int[] numberOrder;
    int[] pointValues;
    String[] questionAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        questListView = findViewById(R.id.questionListView);

        Gson gson = new Gson();
        json = getIntent().getStringExtra("com.example.potatoes.logIn");
        jsonClass = getIntent().getStringExtra("com.example.potatoes.CLASS");
        jsonQuiz = getIntent().getStringExtra("com.example.potatoes.QUIZ");
        user = gson.fromJson(json, User.class);
        userClass = gson.fromJson(jsonClass, Class.class);
        userQuiz = gson.fromJson(jsonQuiz, Quiz.class);

        final ArrayList<Question> questionList = userQuiz.getQuestions();
        numberOrder = new int[questionList.size()];
        pointValues = new int[questionList.size()];
        questionAnswered = new String[questionList.size()];
        int i = 0;
        for (Question object: questionList){
            numberOrder[i] = i+1;
            i++;
        }
        i = 0;
        for (Question object: questionList){
            pointValues[i] = object.getPointValue();
            i++;
        }
        i = 0;
        for (Question object: questionList) {
            questionAnswered[i] = object.isAnswered();
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
                extras.putString("com.example.potatoes.logIn", json);
                extras.putString("com.example.potatoes.CLASS", jsonClass);
                extras.putString("com.example.potatoes.QUIZ", jsonQuiz);
                Gson gson = new Gson();
                jsonQuestion = gson.toJson(questionList.get(position));
                extras.putString("com.example.potatoes.QUESTION", jsonQuestion);
                startActivity(showQuestions);
            }
        });
    }
}
