package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TeacherOverview extends Activity {

    ListView classListView;
    String[] classnames;
    String[] numOfQuizzes;
    String[] numOfOpenQuizzes;
    String[] numOfStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_overview);

        Resources res = getResources();
        classListView = findViewById(R.id.classListView);
        classnames = res.getStringArray(R.array.classnames);
        numOfQuizzes = res.getStringArray(R.array.numOfQuizzes);
        numOfOpenQuizzes = res.getStringArray(R.array.numOfOpenQuizzes);
        numOfStudents = res.getStringArray(R.array.numOfStudents);

        AdapterClass classAdapter = new AdapterClass(this, classnames, numOfQuizzes,
                numOfOpenQuizzes, numOfStudents);
        classListView.setAdapter(classAdapter);

        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showClassQuizzes = new Intent(getApplicationContext(), ClassQuizzes.class);
                showClassQuizzes.putExtra("com.example.potatos.CLASS_INDEX", position);
                startActivity(showClassQuizzes);
            }
        });
    }
}
