package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.common.Class;
import com.example.common.Quiz;
import com.example.common.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TeacherOverview extends Activity {

    ListView classListView;
    User user;
    String json;
    String[] classes;
    int[] numOfQuizzes;
    int[] numOfOpenQuizzes;
    int[] numOfStudents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_overview);

        classListView = findViewById(R.id.classListView);

        Gson gson = new Gson();
        json = getIntent().getStringExtra("com.example.potatoes.logIn");
        user = gson.fromJson(json , User.class);

        final ArrayList<Class> classList = user.getClassesIn();
        classes = new String[classList.size()];
        numOfQuizzes = new int[classList.size()];
        numOfOpenQuizzes = new int[classList.size()];
        numOfStudents = new int[classList.size()];
        int i = 0;
        for (Class object: classList) {
            classes[i] = object.getClassName();
            i++;
        }
        i = 0;
        for (Class object: classList) {
            numOfQuizzes[i] = object.getQuizzes().size();
            i++;
        }
        i = 0;
        for (Class object: classList) {
            int sum = 0;
            for (Quiz quiz: object.getQuizzes()) {
                if (quiz.isOpen())
                    sum++;
            }
            numOfOpenQuizzes[i] = sum;//
            i++;
        }
        i=0;
        for (Class object: classList) {
            numOfStudents[i] = object.getUsers().size();
            i++;
        }


        AdapterClass classAdapter = new AdapterClass(this, classes, numOfQuizzes,
                numOfOpenQuizzes, numOfStudents);
        classListView.setAdapter(classAdapter);

        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showClassQuizzes = new Intent(getApplicationContext(), ClassQuizzes.class);
                Bundle extras = new Bundle();
                extras.putInt("com.example.potatos.CLASS_INDEX", position);
                extras.putString("com.example.potatoes.logIn", json);
                Gson gson = new Gson();
                String jsonClass = gson.toJson(classList.get(position));
                extras.putString("com.example.potatoes.CLASS", jsonClass);

                showClassQuizzes.putExtras(extras);
                startActivity(showClassQuizzes);
            }
        });
    }
}


