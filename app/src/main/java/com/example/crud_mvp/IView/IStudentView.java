package com.example.crud_mvp.IView;

import android.database.Cursor;

public interface IStudentView {

    void listStudent(Cursor cursor);
    void createStudent(String name, String lastName, String studentCode);

}
