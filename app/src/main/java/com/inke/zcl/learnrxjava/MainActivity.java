package com.inke.zcl.learnrxjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action3;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ZCLZCL";
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: ZCL 2018/12/20
        linearLayout = findViewById(R.id.root_view);
        imageView = findViewById(R.id.img);
        textView = findViewById(R.id.text);
        Log.d(TAG, "onCreate: linearLayout VISIBLE:" + (linearLayout.getVisibility() == View.VISIBLE));
        Log.d(TAG, "onCreate: imageView VISIBLE:" + (imageView.getVisibility() == View.VISIBLE));
        Log.d(TAG, "onCreate: textView VISIBLE:" + (textView.getVisibility() == View.VISIBLE));
    }


    private void rxStudent(Student[] students) {
        Subscriber<Course> courseSubscriber = new Subscriber<Course>() {
            @Override
            public void onNext(Course course) {
                Log.d(TAG, "course : " + course.getName());
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        };
        Subscriber<Student> studentSubscriber = new Subscriber<Student>() {
            @Override
            public void onNext(Student student) {
                List<Course> courses = student.getCoursesList();
                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.get(i);
                    Log.d(TAG, "student: " + course.getName());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCoursesList());
                    }
                })
                .subscribe(courseSubscriber);
    }


    class Student {
        private String name;
        private List<Course> coursesList;

        public Student(String name, List<Course> coursesList) {
            this.name = name;
            this.coursesList = coursesList;
        }

        public List<Course> getCoursesList() {
            return coursesList;
        }

        public String getName() {
            return name;
        }
    }

    class Course {
        private String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
