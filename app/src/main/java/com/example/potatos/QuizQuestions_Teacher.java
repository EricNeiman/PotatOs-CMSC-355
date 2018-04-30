package com.example.potatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.common.Question;
import com.example.common.Quiz;
import com.example.common.User;
import com.example.common.Class;

import java.util.ArrayList;

public class QuizQuestions_Teacher extends AppCompatActivity {
    Toolbar toolbar;
    ListView questListView;
    String json;
    String jsonClass;
    String jsonQuiz;
    String jsonQuestion;
    User user;
    Class userClass;
    Quiz userQuiz;
    ArrayList<Question> questionList;
    int[] numberOrder;
    int[] pointValues;
    String[] questionAnswered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions__teacher);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quiz Name"); //Todo, make proper name based on quiz

        createDisplayList();

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showQuestions = new Intent(getApplicationContext(), com.example.potatos.Question.class);
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
    //todo create option menu for quiz if at all.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.joinClass:
                //Go to the join class Activity
                return true;
            case R.id.logOut:
                //return to Main Activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createDisplayList() {
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
    }
}
