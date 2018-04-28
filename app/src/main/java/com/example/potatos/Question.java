package com.example.potatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.common.Answer;
import com.example.common.Quiz;
import com.example.common.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Question extends AppCompatActivity {

    String json;
    String jsonClass;
    String jsonQuiz;
    String jsonQuestion;
    String jsonNewQuestion;
    User user;
    Class userClass;
    Quiz userQuiz;
    ArrayList<com.example.common.Question> questionList;
    com.example.common.Question userQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Gson gson = new Gson();
        json = getIntent().getStringExtra("com.example.potatoes.logIn");
        jsonClass = getIntent().getStringExtra("com.example.potatoes.CLASS");
        jsonQuiz = getIntent().getStringExtra("com.example.potatoes.QUIZ");
        jsonQuestion = getIntent().getStringExtra("com.example.potatoes.QUESTION");
        user = gson.fromJson(json, User.class);
        userClass = gson.fromJson(jsonClass, Class.class);
        userQuiz = gson.fromJson(jsonQuiz, Quiz.class);
        userQuestion = gson.fromJson(jsonQuestion, com.example.common.Question.class);
        questionList = userQuiz.getQuestions();
        final Answer[] answerList = userQuestion.getAnswers();

        //Propagate the answerText and questionText
        TextView questionText = findViewById(R.id.questionTextView);
        TextView answerATextView = findViewById(R.id.answerATextView);
        TextView answerBTextView = findViewById(R.id.answerBTextView);
        TextView answerCTextView = findViewById(R.id.answerCTextView);
        TextView answerDTextView = findViewById(R.id.answerDTextView);

        questionText.setText(userQuestion.getPrompt());
        answerATextView.setText(answerList[1].getAnswerText());
        answerBTextView.setText(answerList[2].getAnswerText());
        answerCTextView.setText(answerList[3].getAnswerText());
        answerDTextView.setText(answerList[4].getAnswerText());

        //Run the Buttons and allow the user to select them.
        createRadioButtons();
        setupNextSelectedButton();
        setupPrevSelectedButton();
    }

    public void setupNextSelectedButton() {
        Button button = findViewById(R.id.nextQuestion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup selection = findViewById(R.id.radio_group_selection);
                int idOfSelected = selection.getCheckedRadioButtonId();
                RadioButton selectedLetter = findViewById(idOfSelected);
                //send the selectedLetter to the QuizClass and check isAnswered();

                //Move to the next question in the Quiz
                int position = getIntent().getIntExtra("com.example.potatos.QUESTION_INDEX", 0);
                Gson gson = new Gson();
                jsonNewQuestion = gson.toJson(questionList.get(position+1));
            }
        });
    }

    public void setupPrevSelectedButton() {
        Button button = findViewById(R.id.prevQuestion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup selection = findViewById(R.id.radio_group_selection);
                int idOfSelected = selection.getCheckedRadioButtonId();
                RadioButton selectedLetter = findViewById(idOfSelected);
                //send the selectedLetter to the QuizClass and check isAnswered();
                int position = getIntent().getIntExtra("com.example.potatos.QUESTION_INDEX", 0);
                Gson gson = new Gson();
                jsonNewQuestion = gson.toJson(questionList.get(position-1));
            }
        });
    }

    public void createRadioButtons() {
        RadioGroup selection = findViewById(R.id.radio_group_selection);

        String[] letter_selections =  getResources().getStringArray(R.array.letter_selection);

        for (int i = 0; i < letter_selections.length; i++) {
            String letter_selection = letter_selections[i];

            RadioButton button = new RadioButton(this);
            button.setText(letter_selection);


            //Set on click event
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //add radio group

            selection.addView(button);
        }
    }
}
