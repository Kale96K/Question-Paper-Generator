package com.example.rushikesh.questionpapergenerator3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generate(View v){
        Intent intent = new Intent(this,FillForm.class);
        startActivity(intent);
    }

    public void admin(View v){
        Intent intent = new Intent(this,AdminLogin.class);
        startActivity(intent);
    }
}
