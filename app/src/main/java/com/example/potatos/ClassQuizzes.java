package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ClassQuizzes extends Activity {

    ListView quizListView;
    String[] quizzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_quizzes);

        Resources res = getResources();
       // quizListView = findViewById(R.id.quizNameListView);
        //quizzes = res.getStringArray(R.array.quizNames);

        AdapterQuiz quizAdapter = new AdapterQuiz(this, quizzes);
        quizListView.setAdapter(quizAdapter);

        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showQuizQuestions = new Intent(getApplicationContext(), QuizQuestions.class);
                showQuizQuestions.putExtra("com.example.potatos.QUIZ_INDEX", position);
                startActivity(showQuizQuestions);
            }
        });
    }
}
