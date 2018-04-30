package com.example.potatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.Class;
import com.example.common.User;

import static com.example.common.REST.ClassREST.getClassById;

public class JoinClass extends AppCompatActivity {

    Toolbar toolbar;
    String json;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        json = getIntent().getStringExtra("com.example.potatos.logIn");
        user = User.fromJson(json);

        Button btn = findViewById(R.id.joinByClassCodeBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This reads the EditText for the ID
                EditText classCode = findViewById(R.id.classCodeEditText);
                String newClassCode = classCode.getText().toString();
                int classID = Integer.parseInt(newClassCode);
                //Converts it to the correct class through getClassByID
                Class newClass = getClassById(classID);
                //Then it takes that class and user given earlier, and adds them to each other.
                user.appendClassesIn(newClass);
                newClass.appendUsers(user);

                finish();
            }
        });
    }
}
