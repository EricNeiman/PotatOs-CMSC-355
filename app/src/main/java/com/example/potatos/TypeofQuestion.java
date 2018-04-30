package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class TypeofQuestion extends Activity {
    private static final int PICK_IMAGE = 100;
    Uri imageUri ;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typeofquestion);
        String quiztext = getIntent().getStringExtra("Questiontext");
        TextView tv = (TextView)findViewById(R.id.TVquestion);
        tv.setText(quiztext);
        String firstanswertext = getIntent().getStringExtra("firstAnswertext");
        TextView tv1 = (TextView)findViewById(R.id.TVfirstAnswer);
        tv1.setText(firstanswertext);
        String secondanswertext = getIntent().getStringExtra("secondAnswertext");
        TextView tv2 = (TextView)findViewById(R.id.TVsecondAnswer);
        tv2.setText(secondanswertext);
        String thirdanswertext = getIntent().getStringExtra("thirdAnswertext");
        TextView tv3 = (TextView)findViewById(R.id.TVthirdAnswer);
        tv3.setText(thirdanswertext);
        String fourthanswertext = getIntent().getStringExtra("fourthAnswertext");
        TextView tv4 = (TextView)findViewById(R.id.TVfourthAnswer);
        tv4.setText(fourthanswertext);

        imageView = (ImageView)findViewById(R.id.imageView3);
        Button button = (Button)findViewById(R.id.Bimage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicture();
            }
        });


    }

    public void openPicture(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  //all for result from selecting image

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            imageUri = data.getData(); // use it if want to take the image to another screen

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ImageView myImageView = (ImageView) findViewById(R.id.imageView3);
            imageView.setImageBitmap(bitmap);
        }
    }

    public void onAddTextClick(View v)  {
        if(v.getId() == R.id.bAddText)  {
            Intent i = new Intent(this, AddText.class);
            startActivity(i);

        }
    }

    public void onAddAnswerClick(View v){
        if(v.getId() == R.id.bAddAnswer) {
            Intent i = new Intent(this, AddAnswer.class);
            startActivity(i);
        }

    }

    public void onSaveQuestionClick(View v){
        if(v.getId() == R.id.bSaveQuestion) {
            Intent i = new Intent(this, CreateQuiz.class);
            startActivity(i);
        }

    }







}