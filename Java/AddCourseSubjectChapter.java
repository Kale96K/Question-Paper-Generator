package com.example.rushikesh.questionpapergenerator3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddCourseSubjectChapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_subject_chapter);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(AddCourseSubjectChapter.this,AddCourse.class);
        startActivity(intent);
    }

    public void addSubject(View view) {
        Intent intent1 = new Intent(AddCourseSubjectChapter.this,AddSubject.class);
        startActivity(intent1);
    }

    public void addChapter(View view) {
        Intent intent2 = new Intent(AddCourseSubjectChapter.this,AddChapter.class);
        startActivity(intent2);
    }
}
