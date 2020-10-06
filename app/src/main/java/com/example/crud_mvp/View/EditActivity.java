package com.example.crud_mvp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud_mvp.BD.DBHelper;
import com.example.crud_mvp.IView.IEditStudentView;
import com.example.crud_mvp.Presenter.IStudentPresenter;
import com.example.crud_mvp.Presenter.StudentPresenter;
import com.example.crud_mvp.R;

public class EditActivity extends AppCompatActivity implements IEditStudentView {

    private IStudentPresenter iStudentPresenter;
    long id;
    DBHelper helper;
    EditText txtName, txtLastName, txtStudentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new DBHelper(this);
        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        txtName = (EditText) findViewById(R.id.txtName_Edit);
        txtLastName = (EditText) findViewById(R.id.txtLastName_Edit);
        txtStudentCode = (EditText) findViewById(R.id.txtStudentCode_Edit);

        iStudentPresenter = new StudentPresenter(this);
        iStudentPresenter.getStudent(id);

    }


    @Override
    public void getStudent(Cursor cursor) {
        if (cursor.moveToNext()) {
            txtName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_name)));
            txtLastName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_lastName)));
            txtStudentCode.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_student_code)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_edit:
                String name = txtName.getText().toString().trim();
                String lastName = txtLastName.getText().toString().trim();
                String studentCode = txtStudentCode.getText().toString().trim();

                if (name.length() > 0 & lastName.length() > 0 & studentCode.length() >0) {

                    ContentValues values = new ContentValues();
                    values.put(DBHelper.row_name, name);
                    values.put(DBHelper.row_lastName, lastName);
                    values.put(DBHelper.row_student_code, studentCode);

                    iStudentPresenter.updateStudent(values, id);
                    Toast.makeText(EditActivity.this, "Successful Edited", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(EditActivity.this, "Something went wrong on Edited", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("You want to deleted this");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    iStudentPresenter.deleteStudent(id);
                    Toast.makeText(EditActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}