package com.example.rushikesh.questionpapergenerator3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FillForm extends AppCompatActivity {

    private Spinner spinnerStream, spinnerYear, spinnerSem;
    private ArrayAdapter<String> adapterStream, adapterYear, adapterSem,adapterSubject;
    public static String selectedSem,selectedStream;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);

        spinnerStream = (Spinner) findViewById(R.id.streamSpinner);
        spinnerYear = (Spinner) findViewById(R.id.yearSpinner);
        spinnerSem = (Spinner) findViewById(R.id.semSpinner);

        adapterStream = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterYear = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterSem = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);


        spinnerStream.setAdapter(adapterStream);
        spinnerSem.setAdapter(adapterSem);
        spinnerYear.setAdapter(adapterYear);

        adapterYear.clear();
        adapterYear.add("1");
        adapterYear.add("2");
        adapterYear.add("3");

        adapterSem.clear();



        spinnerStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedStream = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getItemAtPosition(position).toString();

                if (selectedYear.equals("1")){
                    adapterSem.clear();
                    adapterSem.add("1");
                    adapterSem.add("2");
                }else  if (selectedYear.equals("2")){
                    adapterSem.clear();
                    adapterSem.add("3");
                    adapterSem.add("4");
                }else {
                   adapterSem.clear();
                    adapterSem.add("5");
                    adapterSem.add("6");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedSem = adapterSem.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BackgroundTaskStream bt = new BackgroundTaskStream();
        bt.execute(adapterStream);
    }

    class BackgroundTaskStream extends AsyncTask<ArrayAdapter<String>, Void, String> {

        @Override
        protected String doInBackground(ArrayAdapter<String>... adapters) {

            try {
                URL url = new URL("http://192.168.43.235/getStream.php");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String response = br.readLine();

                String[] names = response.split("-");

                adapters[0].clear();

                for (String name : names) {
                    adapters[0].add(name);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;

        }


    }



    class FillForm1{

        String selectedSem;
        String selectedStream;

        FillForm1(String selectedSem, String selectedStream){

            this.selectedSem=selectedSem;
            this.selectedStream=selectedStream;
        }

    }


    public void done(View v){
        FillForm1 fillForm = new FillForm1(selectedSem,selectedStream);
        BackgroundTaskSubject bt1 = new BackgroundTaskSubject();
        bt1.execute(fillForm);
    }

    class BackgroundTaskSubject extends AsyncTask<FillForm1, Void, String[]> {

        @Override
        protected String[] doInBackground(FillForm1... fillForms) {

            String selectedSem = fillForms[0].selectedSem;
            String selectedStream = fillForms[0].selectedStream;
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

        @Override
        protected void onPostExecute(String[] strings) {
            Intent intent = new Intent(FillForm.this,Subjects.class);
            intent.putExtra("messages",strings);
            startActivity(intent);
            super.onPostExecute(strings);
        }
    }
}
