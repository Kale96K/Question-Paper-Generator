package com.example.rushikesh.questionpapergenerator3;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Subjects extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        Bundle bundle = getIntent().getExtras();
        String[] strings = bundle.getStringArray("messages");
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,R.layout.my_text_view,strings);
        ListView listView = (ListView)findViewById(R.id.LVSubjects);
        listView.setAdapter(adapter);
    }
}
