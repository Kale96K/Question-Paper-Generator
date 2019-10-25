package com.example.rushikesh.questionpapergenerator3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class AddCourse extends AppCompatActivity {
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }

    public void addCourseDone(View view) {
        EditText editText = (EditText)findViewById(R.id.etCourse);
        course = editText.getText().toString();
        BackgroundTaskAddCourse backgroundTaskAddCourse = new BackgroundTaskAddCourse();
        backgroundTaskAddCourse.execute(course);
        editText.setText("");

    }

    class BackgroundTaskAddCourse extends AsyncTask<String,Void,String>{
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(AddCourse.this,"COURSE ADDED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String course = strings[0];

            try {
                URL url = new URL("http://192.168.43.235/addCourse.php?course="+course);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String response = bufferedReader.readLine();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
