package com.example.crud_mvp.Presenter;

import android.content.ContentValues;

public interface IStudentPresenter {
    void getAllStudent();
    void createStudent(ContentValues values);
    void getStudent(long id);
    void updateStudent(ContentValues values, long id);
    void deleteStudent(long id);
}
