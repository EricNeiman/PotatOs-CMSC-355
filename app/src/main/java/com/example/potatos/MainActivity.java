package com.example.potatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logInBtn = findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = findViewById(R.id.usernameEditText);
                EditText passwordEditText = findViewById(R.id.passwordEditText);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                /*Need to do in future Sprint
                    Create Account Verification and judge if student or Teacher account.
                 */
                //This goes to the Teacher's Overview Menu for Sprint1.
                Intent overviewTeacher = new Intent(getApplicationContext(), TeacherOverview.class);
                overviewTeacher.putExtra("com.example.potatoes.logIn", username);
                startActivity(overviewTeacher);
            }
        });
    }
}
