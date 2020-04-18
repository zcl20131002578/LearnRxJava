package com.inke.zcl.learnrxjava.design.singole;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Create By chunliangzhang on 2020-03-20
 * Version 1.0
 * Description:
 */
public class Student implements Parcelable {

    private String name;
    private int age;

    protected Student(Parcel in) {
//        in.writeBooleanArray();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
