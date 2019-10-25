package com.example.rushikesh.questionpapergenerator3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminLogin extends Activity {

    public String value1;
    public String value2;
    EditText editTextUsername;
    EditText editTextPassword;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        editTextUsername = (EditText) findViewById(R.id.etUsername);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        Button button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                value1 = editTextUsername.getText().toString();
                value2 = editTextPassword.getText().toString();

                BackgroundTask bt = new BackgroundTask();
                bt.execute(value1, value2);
                editTextUsername.setText("");
                editTextPassword.setText("");

            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                value1 = editTextUsername.getText().toString();
                value2 = editTextPassword.getText().toString();

                BackgroundTaskLongPress bt1 = new BackgroundTaskLongPress();
                bt1.execute(value1, value2);
                editTextUsername.setText("");
                editTextPassword.setText("");

                return false;
            }
        });

    }



    class BackgroundTask extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);


            if(flag==1){
                Intent intent = new Intent(AdminLogin.this,AdminFillForm.class);
                startActivity(intent);
            }else {
                Toast.makeText(AdminLogin.this,"Login Failed",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Integer doInBackground(String... params) {

            try {
                URL url = new URL("http://192.168.43.235/username.php");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String response = br.readLine();

                URL url1 = new URL("http://192.168.43.235/password.php");
                BufferedReader br1 = new BufferedReader(new InputStreamReader(url1.openStream()));

                String response1 = br1.readLine();

                if (value1.equals(response) && value2.equals(response1)) {

                    flag = 1;
                } else {
                    flag = 0;
                }

            } catch (MalformedURLException e) {
                Log.e("DB",e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("DB",e.toString());
                e.printStackTrace();
            }
            return flag;
        }
    }


    class BackgroundTaskLongPress extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);


            if(flag==1){
                Intent intent = new Intent(AdminLogin.this,AddCourseSubjectChapter.class);
                startActivity(intent);
            }else {
                Toast.makeText(AdminLogin.this,"Login Failed",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Integer doInBackground(String... params) {

            try {
                URL url = new URL("http://192.168.43.235/username.php");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String response = br.readLine();

                URL url1 = new URL("http://192.168.43.235/password.php");
                BufferedReader br1 = new BufferedReader(new InputStreamReader(url1.openStream()));

                String response1 = br1.readLine();

                if (value1.equals(response) && value2.equals(response1)) {

                    flag = 1;
                } else {
                    flag = 0;
                }

            } catch (MalformedURLException e) {
                Log.e("DB",e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("DB",e.toString());
                e.printStackTrace();
            }
            return flag;
        }
    }
}
