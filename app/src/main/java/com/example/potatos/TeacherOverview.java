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

import com.example.common.Class;
import com.example.common.Quiz;
import com.example.common.User;

import java.util.ArrayList;

public class TeacherOverview extends AppCompatActivity {
    Toolbar toolbar;
    ListView classListView;
    User user;
    String json;
    ArrayList<Class> classList;
    String[] classes;
    int[] numOfQuizzes;
    int[] numOfOpenQuizzes;
    int[] numOfStudents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_overview);
        //Setup overhead toolbar with appropriate options
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("The Quiz App");


        //Create the class display;
        createDisplayList();

        //When the person clicks on of the List items, go to the class.
        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showClassQuizzes = new Intent(getApplicationContext(), ClassQuizzes.class);
                Bundle extras = new Bundle();
                extras.putInt("com.example.potatos.CLASS_INDEX", position);
                extras.putString("com.example.potatos.logIn", json);
                String jsonClass = classList.get(position).toJson();
                extras.putString("com.example.potatos.CLASS", jsonClass);

                showClassQuizzes.putExtras(extras);
                startActivity(showClassQuizzes);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.createClass:
                //Go to the join class Activity
                Intent createClass = new Intent(getApplicationContext(), CreateClass.class);
                createClass.putExtra("com.example.potatos.logIn", json);
                startActivity(createClass);
                return true;
            case R.id.logOut:
                //return to Main Activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createDisplayList() {
        classListView = findViewById(R.id.classListView);

        json = getIntent().getStringExtra("com.example.potatos.logIn");
        user = User.fromJson(json);

        classList = user.getClassesIn();
        classes = new String[classList.size()];
        numOfQuizzes = new int[classList.size()];
        numOfOpenQuizzes = new int[classList.size()];
        numOfStudents = new int[classList.size()];
        int i = 0;
        for (Class object : classList) {
            classes[i] = object.getClassName();
            i++;
        }
        i = 0;
        for (Class object : classList) {
            numOfQuizzes[i] = object.getQuizzes().size();
            i++;
        }
        i = 0;
        for (Class object : classList) {
            int sum = 0;
            for (Quiz quiz : object.getQuizzes()) {
                if (quiz.isOpen())
                    sum++;
            }
            numOfOpenQuizzes[i] = sum;//
            i++;
        }
        i = 0;
        for (Class object : classList) {
            numOfStudents[i] = object.getUsers().size();
            i++;
        }


        AdapterClass classAdapter = new AdapterClass(this, classes, numOfQuizzes,
                numOfOpenQuizzes, numOfStudents);
        classListView.setAdapter(classAdapter);
    }
}


