package com.example.rushikesh.questionpapergenerator3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestionManipulation extends AppCompatActivity {

    public String selectedSem,selectedStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_manipulation);

        Bundle bundle = getIntent().getExtras();
         selectedSem = bundle.getString("selectedSem");
        selectedStream = bundle.getString("selectedStream");
    }

    public void addQuestion(View v ){
        Intent intent = new Intent(this,AdminAddQuestion.class);
        intent.putExtra("selectedSem",selectedSem);
        intent.putExtra("selectedStream",selectedStream);
        startActivity(intent);
    }

    public void deleteQuestion(View view) {
    }

    public void qppQuestion(View view) {
    }
}
