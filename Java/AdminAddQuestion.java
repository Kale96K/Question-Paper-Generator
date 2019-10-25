package com.example.rushikesh.questionpapergenerator3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminAddQuestion extends AppCompatActivity {

    private Spinner spinnerAdminMarks;
    private Spinner spinnerAdminSubject;
    private Spinner spinnerAdminChapter;
    private Spinner spinnerAdminLevel;
    private ArrayAdapter<String> adapterAdminMarks;
    private ArrayAdapter<String> adapterAdminSubject;
    private ArrayAdapter<String> adapterAdminChapter;
    private ArrayAdapter<String> adapterAdminLevel;
    String selectedSem,selectedStream,question1;
    public static String selectedSubject,selectedChapter,selectedMarks,selectedLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_question);

        Bundle bundle = getIntent().getExtras();
        selectedSem = bundle.getString("selectedSem");
        selectedStream = bundle.getString("selectedStream");

        spinnerAdminMarks = (Spinner) findViewById(R.id.spinnerMarks);
        spinnerAdminSubject = (Spinner) findViewById(R.id.spinnerSubject);
        spinnerAdminChapter = (Spinner) findViewById(R.id.spinnerChapter);
        spinnerAdminLevel = (Spinner) findViewById(R.id.spinnerLevel);

        adapterAdminMarks = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterAdminSubject = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterAdminChapter = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterAdminLevel = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);


        spinnerAdminMarks.setAdapter(adapterAdminMarks);
        spinnerAdminChapter.setAdapter(adapterAdminChapter);
        spinnerAdminSubject.setAdapter(adapterAdminSubject);
        spinnerAdminLevel.setAdapter(adapterAdminLevel);

        adapterAdminMarks.clear();
        adapterAdminMarks.add("2");
        adapterAdminMarks.add("4");
        adapterAdminMarks.add("6");
        adapterAdminMarks.add("8");




        spinnerAdminLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLevel = adapterAdminLevel.getItem(position).toString();
                BackgroundTaskSubject task = new BackgroundTaskSubject();
                task.execute(selectedSem,selectedStream);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spinnerAdminSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubject = adapterAdminSubject.getItem(position).toString();
                BackgroundTaskChapter task1 = new BackgroundTaskChapter();
                task1.execute(selectedSubject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerAdminChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedChapter = adapterAdminChapter.getItem(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerAdminMarks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMarks = adapterAdminMarks.getItem(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        BackgroundTaskLevel backgroundTaskLevel = new BackgroundTaskLevel();
        backgroundTaskLevel.execute();



    }


    public void adminAddDone(View view) {
        EditText editText = (EditText)findViewById(R.id.et1);
        question1 = editText.getText().toString();

        BackgroundTaskAddQuestion backgroundTaskAddQuestion = new BackgroundTaskAddQuestion();
        backgroundTaskAddQuestion.execute(question1,selectedMarks,selectedSubject,selectedChapter,selectedLevel);

        editText.setText("");
        spinnerAdminMarks.setSelection(0);
        spinnerAdminSubject.setSelection(0);
        spinnerAdminChapter.setSelection(0);
        spinnerAdminLevel.setSelection(0);
    }



    class BackgroundTaskSubject extends AsyncTask<String,Void,String[]>{

        @Override
        protected void onPostExecute(String[] strings) {
            adapterAdminSubject.clear();
            adapterAdminSubject.addAll(strings);
            super.onPostExecute(strings);
        }

        @Override
        protected String[] doInBackground(String... fillForm1s) {


            String selectedSem = fillForm1s[0];
            String selectedStream = fillForm1s[1];
            String[] names = new String[0];

            try {
                URL url4 = new URL("http://192.168.43.235/getSubject.php?sem=" + selectedSem + "&stream=" + selectedStream);

                BufferedReader br4 = new BufferedReader(new InputStreamReader(url4.openStream()));

                String response3 = br4.readLine();

                names = response3.split("-");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return names;
        }
    }



    class BackgroundTaskChapter extends AsyncTask<String,Void,String[]>{
        @Override
        protected void onPostExecute(String[] strings) {
            adapterAdminChapter.clear();
            adapterAdminChapter.addAll(strings);
            super.onPostExecute(strings);
        }

        @Override
        protected String[] doInBackground(String... strings) {
            String selectedSubject = strings[0];

            String[] chapters = new String[0];
            try {

                URL url4 = new URL("http://192.168.43.235/getChapter.php?selectedSubject=" + selectedSubject);

                BufferedReader br4 = new BufferedReader(new InputStreamReader(url4.openStream()));

                String response3 = br4.readLine();

                chapters = response3.split("-");


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }return chapters;
        }
    }

    class BackgroundTaskAddQuestion extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(AdminAddQuestion.this,"QUESTION SUCCESSFULLY ADDED",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {

            String question = strings[0];
            String marks = strings[1];
            String subject = strings[2];
            String chapter = strings[3];
            String level = strings[4];

            try {
                URL url5 = new URL("http://192.168.43.235/addQuestion.php?question="+question+"&marks="+marks+"&subject="+subject+"&chapter="+chapter+"&level="+level);
                BufferedReader br5 = new BufferedReader(new InputStreamReader(url5.openStream()));
                String response5 = br5.readLine();
                return response5;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }

    class BackgroundTaskLevel extends AsyncTask<String,Void,String[]>{

        @Override
        protected void onPostExecute(String[] strings) {

            adapterAdminLevel.clear();
            adapterAdminLevel.addAll(strings);
            super.onPostExecute(strings);
        }

        @Override
        protected String[] doInBackground(String... strings) {
            URL url6 = null;
            String[] levels = new String[0];
            try {
                url6 = new URL("http://192.168.43.235/getLevel.php");
                BufferedReader br5 = new BufferedReader(new InputStreamReader(url6.openStream()));
                String response5 = br5.readLine();

                 levels = response5.split("-");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return levels;
        }
    }


}
