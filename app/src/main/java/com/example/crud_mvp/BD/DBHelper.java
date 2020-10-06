package com.example.crud_mvp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static final String database_name = "db_student";
    public static final String table_name = "student";

    public static final String row_id = "_id";
    public static final String row_name = "name";
    public static final String row_lastName = "last_name";
    public static final String row_student_code = "student_code";



    public DBHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + " ( " + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + row_name + " TEXT, " + row_lastName + " TEXT, " + row_student_code + " TEXT)";
        db.execSQL(query);
        db.execSQL("insert into student(name, last_name, student_code) values('Erwin', 'Walker', '201810010')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






}
