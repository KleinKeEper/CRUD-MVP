package com.example.crud_mvp.Presenter;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.crud_mvp.IView.IEditStudentView;
import com.example.crud_mvp.Model.IStudent;
import com.example.crud_mvp.Model.StudentModel;
import com.example.crud_mvp.IView.IStudentView;

public class StudentPresenter implements IStudentPresenter {

    private IStudent iStudentModel;
    private IStudentView iStudentView;
    private IEditStudentView iEditStudentView;

    public StudentPresenter(IStudentView iStudentView) {
        this.iStudentView = iStudentView;
        iStudentModel = new StudentModel(iStudentView);
    }

    public StudentPresenter(IEditStudentView iEditStudentView) {
        this.iEditStudentView = iEditStudentView;
        iStudentModel = new StudentModel(iEditStudentView);
    }


    @Override
    public void getAllStudent() {
        Cursor cursor = iStudentModel.getAllStudent();
        iStudentView.listStudent(cursor);
    }

    @Override
    public void createStudent(ContentValues values) {
        iStudentModel.createStudent(values);
    }

    @Override
    public void getStudent(long id) {
        Cursor cursor = iStudentModel.getStudent(id);
        iEditStudentView.getStudent(cursor);
    }

    @Override
    public void updateStudent(ContentValues values, long id) {
        iStudentModel.updateStudent(values, id);
    }

    @Override
    public void deleteStudent(long id) {
        iStudentModel.deleteStudent(id);
    }
}
