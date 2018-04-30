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
import android.widget.Button;
import android.widget.ListView;

import com.example.common.Class;
import com.example.common.Quiz;
import com.example.common.User;

import java.util.ArrayList;

public class ClassQuizzes extends AppCompatActivity {
    Toolbar toolbar;
    ListView quizListView;
    String json;
    String jsonClass;
    String jsonQuiz;
    User user;
    Class userClass;
    ArrayList<Quiz> quizList;
    String[] quizzes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_quizzes);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Class Name");

        createDisplayList();

        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showQuizQuestions = new Intent(getApplicationContext(), QuizQuestions.class);
                Bundle extras = new Bundle();
                extras.putInt("com.example.potatos.QUIZ_INDEX", position);
                extras.putString("com.example.potatos.logIn", json);
                extras.putString("com.example.potatos.CLASS", jsonClass);
                extras.putString("com.example.potatos.QUIZ", quizList.get(position).toJson());
                showQuizQuestions.putExtras(extras);
                startActivity(showQuizQuestions);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //todo change these to the new option_menu_X
        switch (item.getItemId()){
            case R.id.logOutBtn:
                //return to Main Activity
                logOff();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createDisplayList() {
        json = getIntent().getStringExtra("com.example.potatos.logIn");
        user = User.fromJson(json);
        jsonClass = getIntent().getStringExtra("com.example.potatos.CLASS");
        userClass = Class.fromJson(jsonClass);

        quizList = userClass.getQuizzes();
        int i = 0;
        for (Quiz object: quizList){
            quizzes[i] = object.getQuizName();
            i++;
        }


        AdapterQuiz quizAdapter = new AdapterQuiz(this, quizzes);
        quizListView.setAdapter(quizAdapter);
    }

    public void logOff() {
        Intent logOut = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(logOut);
    }
}
