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

public class AddChapter extends AppCompatActivity {
    private Spinner spinnerFillStream;
    private Spinner spinnerFillSubject;
    private Spinner spinnerFillSem;
    private ArrayAdapter<String> adapterFillStream;
    private ArrayAdapter<String> adapterFillSubject;
    private ArrayAdapter<String> adapterFillSem;
    public static String selectedStream,selectedSubject,selectedSem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);

        spinnerFillStream = (Spinner) findViewById(R.id.spinnerAddChapterStream);
        spinnerFillSem = (Spinner) findViewById(R.id.spinnerAddChapterSem);
        spinnerFillSubject = (Spinner) findViewById(R.id.spinnerAddChapterSubject);


        adapterFillStream = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterFillSubject = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterFillSem = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);

        spinnerFillStream.setAdapter(adapterFillStream);
        spinnerFillSubject.setAdapter(adapterFillSubject);
        spinnerFillSem.setAdapter(adapterFillSem);

        adapterFillSem.clear();

        adapterFillSem.add("5");
        adapterFillSem.add("6");

        BTFillStream btFillStream = new BTFillStream();
        btFillStream.execute(adapterFillStream);

        spinnerFillStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStream = adapterFillStream.getItem(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFillSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubject = adapterFillSubject.getItem(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFillSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSem = adapterFillSem.getItem(position).toString();

                BTFillSubject btFillSubject = new BTFillSubject();
                btFillSubject.execute(selectedSem,selectedStream);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void addChapterDone(View view) {

        EditText editText = (EditText)findViewById(R.id.etChapter);
        String chapter = editText.getText().toString();
        BTAddChapter btAddChapter = new BTAddChapter();
        btAddChapter.execute(selectedSubject,chapter);

        editText.setText("");
        spinnerFillSem.setSelection(0);
        spinnerFillSubject.setSelection(0);
        spinnerFillStream.setSelection(0);

    }


    class BTFillStream extends AsyncTask<ArrayAdapter<String>,Void,String[]>{


        @Override
        protected String[] doInBackground(ArrayAdapter<String>... strings) {

            try{
                URL url=new URL("http://192.168.43.235/getStream.php");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String response = br.readLine();

                String[] names = response.split("-");

                strings[0].clear();

                for(String name: names){
                    strings[0].add(name);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new String[0];
        }
    }


    class BTFillSubject extends AsyncTask<String,Void,String[]>{

        @Override
        protected void onPostExecute(String[] strings) {
            adapterFillSubject.clear();
            adapterFillSubject.addAll(strings);
            super.onPostExecute(strings);
        }

        @Override
        protected String[] doInBackground(String... arrayAdapters) {

            String selectedSem = arrayAdapters[0];
            String selectedStream = arrayAdapters[1];
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

    class BTAddChapter extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(AddChapter.this,"CHAPTER SUCCESSFULLY ADDED",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {

            String selectedSubject = strings[0];
            String chapter = strings[1];

            try {
                URL  url = new URL("http://192.168.43.235/addChapter.php?chapter="+chapter+"&subject="+selectedSubject);
                BufferedReader br5 = new BufferedReader(new InputStreamReader(url.openStream()));
                String response5 = br5.readLine();
                return response5;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
