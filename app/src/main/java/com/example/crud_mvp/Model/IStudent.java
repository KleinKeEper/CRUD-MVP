package com.example.crud_mvp.Model;

import android.content.ContentValues;
import android.database.Cursor;

public interface IStudent {
    Cursor getAllStudent();
    void createStudent(ContentValues values);
    Cursor getStudent(Long id);
    void updateStudent(ContentValues values, long id);
    void deleteStudent(long id);
}
