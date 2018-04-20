package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddAnswer extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addanswer);
    }

    public  void onSaveAnswerClick(View v) {
        if(v.getId() == R.id.bSaveAnswers) {

            EditText a = (EditText)findViewById(R.id.TFfirstAnswer);
            String str1 = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFsecondAnswer);
            String str2 = b.getText().toString();
            EditText c = (EditText)findViewById(R.id.TFthirdAnswer);
            String str3 = c.getText().toString();
            EditText d = (EditText)findViewById(R.id.TFfourthAnswer);
            String str4 = a.getText().toString();
            Intent i = new Intent(this, TypeofQuestion.class);
            i.putExtra("firstAnswertext", str1);
            i.putExtra("secondAnswertext", str2);
            i.putExtra("thirdAnswertext", str3);
            i.putExtra("fourthAnswertext", str4);
            startActivity(i);
        }

    }


}
