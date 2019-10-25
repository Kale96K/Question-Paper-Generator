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

public class AdminFillForm extends AppCompatActivity {

    private Spinner spinnerAdminStream;
    private Spinner spinnerAdminYear;
    private Spinner spinnerAdminSem;
    private ArrayAdapter<String> adapterAdminStream;
    private ArrayAdapter<String> adapterAdminYear;
    private ArrayAdapter<String> adapterAdminSem;
    String selectedSem,selectedStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_fill_form);
        spinnerAdminStream = (Spinner) findViewById(R.id.spinnerStream);
        spinnerAdminYear = (Spinner) findViewById(R.id.spinnerYear);
        spinnerAdminSem = (Spinner) findViewById(R.id.spinnerSem);

        adapterAdminStream = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterAdminYear = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        adapterAdminSem = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);


        spinnerAdminStream.setAdapter(adapterAdminStream);
        spinnerAdminYear.setAdapter(adapterAdminYear);
        spinnerAdminSem.setAdapter(adapterAdminSem);

        adapterAdminYear.clear();
        adapterAdminYear.add("1");
        adapterAdminYear.add("2");
        adapterAdminYear.add("3");







        spinnerAdminYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("1")){
                    adapterAdminSem.clear();
                    adapterAdminSem.add("1");
                    adapterAdminSem.add("2");
                }else if (selectedItem.equals("2")){
                    adapterAdminSem.clear();
                    adapterAdminSem.add("3");
                    adapterAdminSem.add("4");
                }else {
                    adapterAdminSem.clear();
                    adapterAdminSem.add("5");
                    adapterAdminSem.add("6");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerAdminSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSem = adapterAdminSem.getItem(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerAdminStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStream = adapterAdminStream.getItem(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        BackgroundTask bt = new BackgroundTask();
        bt.execute(adapterAdminStream);

    }


    class BackgroundTask extends AsyncTask<ArrayAdapter<String>,Void,Void> {

        @Override
        protected Void doInBackground(ArrayAdapter<String>... adapters) {

            try{
                URL url=new URL("http://192.168.43.235/getStream.php");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String response = br.readLine();

                String[] names = response.split("-");

                adapters[0].clear();

                for(String name: names){
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

    public void adminDone(View v){
        Intent intent = new Intent(this,QuestionManipulation.class);
        intent.putExtra("selectedSem",selectedSem);
        intent.putExtra("selectedStream",selectedStream);
        startActivity(intent);
    }
}
