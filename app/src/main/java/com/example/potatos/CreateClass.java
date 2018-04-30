package com.example.potatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.Class;
import com.example.common.User;

public class CreateClass extends AppCompatActivity {
    Toolbar toolbar;
    String json;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = findViewById(R.id.createClassBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText className = findViewById(R.id.classNameEditText);
                EditText className2 = findViewById(R.id.classNameEditText2);
                EditText classCode = findViewById(R.id.classCodeEditText);
                EditText classCode2 = findViewById(R.id.classCodeEditText2);
                json = getIntent().getStringExtra("com.example.potatos.logIn");
                user = User.fromJson(json);

                if (className.getText().toString().equals(className2.getText().toString())) {
                    if (classCode.getText().toString().equals(classCode2.getText().toString())) {
                        Class newClass = new Class(user.getId(), className.getText().toString(),
                                classCode.getText().toString(), null, null);
                        user.appendClassesOwned(newClass);
                        finish();
                    }
                }
            }
        });
    }
}
