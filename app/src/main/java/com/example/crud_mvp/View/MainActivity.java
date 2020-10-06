package com.example.crud_mvp.View;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.crud_mvp.BD.DBHelper;
import com.example.crud_mvp.IView.IStudentView;
import com.example.crud_mvp.Presenter.IStudentPresenter;
import com.example.crud_mvp.Presenter.StudentPresenter;
import com.example.crud_mvp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IStudentView, AdapterView.OnItemClickListener{

    private IStudentPresenter iStudentPresenter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        iStudentPresenter = new StudentPresenter(this);
        iStudentPresenter.getAllStudent();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                CreateDialog createDialog = new CreateDialog();
                createDialog.show(getSupportFragmentManager(), "Create Dialog");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listStudent(Cursor cursor) {
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);
    }

    @Override
    public void createStudent(String name, String lastName, String studentCode) {

        if (name.trim().length() > 0 && lastName.trim().length() > 0 && studentCode.trim().length() > 0) {

            ContentValues values = new ContentValues();
            values.put(DBHelper.row_name, name);
            values.put(DBHelper.row_lastName, lastName);
            values.put(DBHelper.row_student_code, studentCode);

            iStudentPresenter.createStudent(values);
            Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();
            iStudentPresenter.getAllStudent();

        } else {
            Toast.makeText(MainActivity.this, "Nothing to save", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        TextView listId = (TextView) view.findViewById(R.id.listId);
        final long id = Long.parseLong(listId.getText().toString());
        System.out.println("id " + id);

        Intent editView = new Intent(MainActivity.this, EditActivity.class);
        editView.putExtra(DBHelper.row_id, id);
        startActivity(editView);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        iStudentPresenter.getAllStudent();
    }
}