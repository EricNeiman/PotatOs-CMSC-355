package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddText extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtext);
    }

    public  void onSaveTextClick(View v) {
        if(v.getId() == R.id.BquestionText) {

            EditText a = (EditText)findViewById(R.id.TFquestion);
            String str = a.getText().toString();
            Intent i = new Intent(this, TypeofQuestion.class);
            i.putExtra("Questiontext", str);
            startActivity(i);
        }

    }
}
