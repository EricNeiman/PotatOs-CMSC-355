package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class QuizQuestions extends Activity {

    ListView questListView;
    String[] numberOrder;
    String[] pointValues;
    String[] questionAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        Resources res = getResources();
        questListView = findViewById(R.id.questionListView);
        numberOrder = res.getStringArray(R.array.numberOrder);
        pointValues = res.getStringArray(R.array.pointValues);
        questionAnswered = res.getStringArray(R.array.questionAnswered);

        AdapterQuestion questionAdapter = new AdapterQuestion(this, numberOrder, pointValues, questionAnswered);
        questListView.setAdapter(questionAdapter);

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showQuestions = new Intent(getApplicationContext(), Question.class);
                showQuestions.putExtra("com.example.potatos.QUIZ_INDEX", position);
                startActivity(showQuestions);
            }
        });
    }
}
