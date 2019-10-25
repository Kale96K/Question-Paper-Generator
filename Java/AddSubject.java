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

public class AddSubject extends AppCompatActivity {

    private Spinner spinnerAddStream;
    private ArrayAdapter<String> adapterAddStream;
    public static String selectedStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        spinnerAddStream = (Spinner) findViewById(R.id.spinnerAddStream);
        adapterAddStream = new ArrayAdapter<String>(this, R.layout.my_text_view_spinner);
        spinnerAddStream.setAdapter(adapterAddStream);

        spinnerAddStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStream = adapterAddStream.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        BackgroundTaskFillStream backgroundTaskFillStream = new BackgroundTaskFillStream();
        backgroundTaskFillStream.execute(adapterAddStream);
    }


    public void addSubjectDone(View view) {

        EditText editText = (EditText)findViewById(R.id.etSubject);
        String subject = editText.getText().toString();
        EditText editText1 = (EditText)findViewById(R.id.etSem);
        String sem = editText1.getText().toString();

        BackgroundTaskAddSubject backgroundTaskAddSubject = new BackgroundTaskAddSubject();
        backgroundTaskAddSubject.execute(selectedStream,subject,sem);

        editText.setText("");
        editText1.setText("");
        spinnerAddStream.setSelection(0);


    }

    class BackgroundTaskFillStream extends AsyncTask<ArrayAdapter<String>,Void,String[]>{

        @Override
        protected String[] doInBackground(ArrayAdapter<String>... strings) {
            try {


                URL url = new URL("http://192.168.43.235/getStream.php");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String response = br.readLine();

                String[] names = response.split("-");

                strings[0].clear();

                for (String name : names) {
                    strings[0].add(name);
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new String[0];
        }
    }

    class BackgroundTaskAddSubject extends AsyncTask<String,Void,String>{
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(AddSubject.this,"SUBJECT ADDED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = strings[0];
            String subject = strings[1];
            String sem = strings[2];

            try {
                URL url = new URL("http://192.168.43.235/addSubject.php?stream="+stream+"&subject="+subject+"&sem="+sem);
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
