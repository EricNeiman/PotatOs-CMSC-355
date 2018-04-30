package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateAccount extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);
    }
    public  void onRegisterClick(View v) {
        if (v.getId() == R.id.bRegister) {

            EditText a = (EditText) findViewById(R.id.TFusername);
            String str1 = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpass);
            String str2 = b.getText().toString();
            EditText c = (EditText) findViewById(R.id.TFpass1);
            String str3 = c.getText().toString();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}

