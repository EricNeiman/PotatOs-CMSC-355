package com.example.potatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public  void onLogInClick(View v) {
        if(v.getId() == R.id.logInBtn) {

            Intent i = new Intent(MainActivity.this, ClassQuizzes.class);
            startActivity(i);
        }

    }

    public  void onCreateAccountClick(View v) {
        if(v.getId() == R.id.newAccountBtn) {

            Intent i = new Intent(MainActivity.this, CreateAccount.class);
            startActivity(i);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logInBtn = findViewById(R.id.logInBtn);
       /*logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = findViewById(R.id.emailEditText);
                EditText passwordEditText = findViewById(R.id.passwordEditText);
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                User user = getByEmailPass(email, password);

                if (user != null) {
                    if (user.getIsTeacher()){
                        Gson gson = new Gson();
                        String requestJson = gson.toJson(user);

                        Intent overviewTeacher = new Intent(getApplicationContext(), TeacherOverview.class);
                        overviewTeacher.putExtra("com.example.potatoes.logIn", requestJson);
                        startActivity(overviewTeacher);
                    }
                    else {
                        Gson gson = new Gson();
                        String requestJson = gson.toJson(user);

                        Intent overviewTeacher = new Intent(getApplicationContext(), TeacherOverview.class);
                       // overviewTeacher.putExtra("com.example.potatoes.logIn", user);
                        startActivity(overviewTeacher);
                    }
                }
            }
        });*/

        Button newAccountBtn = findViewById(R.id.newAccountBtn);
      /*  newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

}
