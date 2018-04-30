package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.User;

import static com.example.common.REST.UserREST.createUser;

public class CreateAccount extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = findViewById(R.id.registerAccountBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.usernameEditText);
                EditText email = findViewById(R.id.emailEditText);
                EditText password = findViewById(R.id.passwordEditText);
                EditText password2 = findViewById(R.id.passwordConfirmEditText);
                CheckBox teacher = findViewById(R.id.teacherCheckBox);

                if (password.getText().equals(password2.getText())) {
                    if (teacher.isChecked()) {
                        User newTeacher = new User(true, null, null, username.getText().toString(),
                                email.getText().toString(), password.getText().toString(), 0);
                        createUser(newTeacher);
                    }
                    else {
                        User newStudent = new User(true, null, null, username.getText().toString(),
                                email.getText().toString(), password.getText().toString(), 0);
                        createUser(newStudent);
                    }
                    finish();
                }
                else {
                    Toast.makeText(CreateAccount.this, "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

