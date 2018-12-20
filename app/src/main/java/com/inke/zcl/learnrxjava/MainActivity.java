package com.inke.zcl.learnrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: ZCL 2018/12/20
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
